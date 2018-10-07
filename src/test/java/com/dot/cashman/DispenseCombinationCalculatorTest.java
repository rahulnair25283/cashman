
package com.dot.cashman;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.dot.cashstore.CashStore;
import com.dot.cashstore.CashStoreException;

@RunWith(MockitoJUnitRunner.class)
public class DispenseCombinationCalculatorTest {

    private DispenseCombinationCalculator calculator = new DispenseCombinationCalculator();

    private CashStore cashStore;
    
    @Before
    public void setup() {
        cashStore = CashStore.openWith(8, 3);
    }
    
    @After
    public void tearDown() throws CashStoreException {
        cashStore.destroy();
    }

    @Test
    public void shouldCalculateLegalDispenseCombinations() throws DispenseCombinationException {
        assertThat(calculator.calculateFor(20, cashStore), hasItem(new Integer[] { 1, 0 }));
        assertThat(calculator.calculateFor(40, cashStore), hasItem(new Integer[] { 2, 0 }));
        assertThat(calculator.calculateFor(50, cashStore), hasItem(new Integer[] { 0, 1 }));
        assertThat(calculator.calculateFor(60, cashStore), hasItem(new Integer[] { 3, 0 }));
        assertThat(calculator.calculateFor(70, cashStore), hasItem(new Integer[] { 1, 1 }));
        assertThat(calculator.calculateFor(80, cashStore), hasItem(new Integer[] { 4, 0 }));
        assertThat(calculator.calculateFor(100, cashStore),
                hasItems(new Integer[] { 5, 0 }, new Integer[] { 0, 2 }));
        assertThat(calculator.calculateFor(110, cashStore), hasItem(new Integer[] { 3, 1 }));
        assertThat(calculator.calculateFor(200, cashStore), hasItem(new Integer[] { 5, 2 }));
    }
    
    @Test(expected = DispenseCombinationException.class)
    public void shouldThrowErrorIfLegalDispenseCombinationCannotBeCalculated() throws DispenseCombinationException {
        assertThat(calculator.calculateFor(30, cashStore).size(), is(0));
    }
}
