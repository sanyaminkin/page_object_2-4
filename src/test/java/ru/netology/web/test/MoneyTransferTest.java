package ru.netology.web.test;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;
@Data

class MoneyTransferTest {
    DashboardPage dashboardPage;
    int amount;
    String amountString = Integer.toString(amount);

    @BeforeEach
    void browserSetup(){
        var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        int startBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int startBalanceSecondCard = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyToFirstCardFromSecondCardTest() {
        amount = 1;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        var returnPage = topUpPage.SuccessfullMoneyTransfer(amountString, cardNumber);

    }
}
