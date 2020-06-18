package edu.iis.mto.testreactor.atm;

import static org.hamcrest.MatcherAssert.assertThat;

import edu.iis.mto.testreactor.atm.bank.AuthorizationException;
import edu.iis.mto.testreactor.atm.bank.AuthorizationToken;
import edu.iis.mto.testreactor.atm.bank.Bank;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ATMachineTest {

    private Bank bank = Mockito.mock(Bank.class);
    private ATMachine atMachine;

    @BeforeEach
    public void setUp() throws Exception {

        atMachine = new ATMachine(bank, Money.DEFAULT_CURRENCY);

    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

    public MoneyDeposit createSampleDeposit(){

        BanknotesPack zl100pack = BanknotesPack.create(1,Banknote.PL_100);
        List<BanknotesPack> deposit = new ArrayList<>();
        deposit.add(zl100pack);
        return MoneyDeposit.create(Money.DEFAULT_CURRENCY, deposit);
    }
    

    @Test
    void AtmWithSufficientAmountOfMoneyShouldWithdrawInSuccess() throws ATMOperationException, AuthorizationException {

        atMachine.setDeposit(createSampleDeposit());
        Money money = new Money(100,Money.DEFAULT_CURRENCY);



        Withdrawal result = atMachine.withdraw(PinCode.createPIN(1,2,3,4), Card.create("1234"), money);

        Mockito.when(bank.autorize("1234","1234")).thenReturn(AuthorizationToken.create("1"));

        List<BanknotesPack> expectedMoney = new ArrayList<>();
        expectedMoney.add(BanknotesPack.create(1,Banknote.PL_100));

        Withdrawal expected = Withdrawal.create(expectedMoney);

        Assertions.assertEquals(result,expected);


    }


}
