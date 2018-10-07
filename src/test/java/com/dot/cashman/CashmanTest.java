package com.dot.cashman;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dot.cashman.Cashman;
import com.dot.cashstore.CashStore;
import com.dot.cashstore.CashStoreException;
import com.dot.cashstore.Denomination;

public class CashmanTest {
    
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
    public void shouldDispenseGivenAmount() throws CashStoreException {
        Cashman cashman = Cashman.createWith(cashStore);
        cashman.dispense(20);
        
        assertThat(cashStore.getNumberOf(Denomination.TWENTY), is(7));
        
    }
}
