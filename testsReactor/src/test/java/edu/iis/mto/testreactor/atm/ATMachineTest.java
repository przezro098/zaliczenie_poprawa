package edu.iis.mto.testreactor.atm;

import static org.hamcrest.MatcherAssert.assertThat;

import edu.iis.mto.testreactor.atm.bank.Bank;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Currency;

public class ATMachineTest {

    private Bank bank = Mockito.mock(Bank.class);
    private Currency currency = Mockito.mock(Currency.class);

    private ATMachine atMachine;

    @BeforeEach
    public void setUp() throws Exception {
        atMachine = new ATMachine(bank, currency);

    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

    

}
