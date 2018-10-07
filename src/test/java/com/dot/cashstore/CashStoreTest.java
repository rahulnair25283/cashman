package com.dot.cashstore;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CashStoreTest {

    private CashStore cashStore;

    @Before
    public void setupTest() {
        cashStore = CashStore.openWith(10, 10);
    }

    @After
    public void tearDownTest() throws CashStoreException {
        cashStore.destroy();
    }

    @Test
    public void shouldInitialiseStoreWithGivenCurrencies() throws Exception {
        assertThat(cashStore.getNumberOf(Denomination.TWENTY), is(10));
        assertThat(cashStore.getNumberOf(Denomination.FIFTY), is(10));
    }

    @Test
    public void shouldAddNotesToStore() throws Exception {
        cashStore.addNotes(Denomination.TWENTY, 5);
        assertThat(cashStore.getNumberOf(Denomination.TWENTY), is(15));
    }

    @Test
    public void shouldRemoveNotesFromStore() throws Exception {
        cashStore.removeNotes(Denomination.FIFTY, 4);
        assertThat(cashStore.getNumberOf(Denomination.FIFTY), is(6));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfNumberOfNotesToBeRemovedIsMoreThanNumberOfNotesInStore()
            throws Exception {
        cashStore.removeNotes(Denomination.FIFTY, 15);
        assertThat(cashStore.getNumberOf(Denomination.FIFTY), is(10));
    }
}
