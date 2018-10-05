package com.dot;

import org.junit.Test;

public class CashmanTest {

    @Test
    public void shouldTestSomething() throws Exception {

        Cashman cm = new Cashman();
        CashStore cashStore = CashStore.openWith(5, 5);

        cm.something(cashStore, 110);
    }
}
