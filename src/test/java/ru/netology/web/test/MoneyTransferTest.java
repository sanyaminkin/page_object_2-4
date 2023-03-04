package ru.netology.web.test;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Data

class MoneyTransferTest {
    int amount;
    int startBalanceFirstCard;
    int startBalanceSecondCard;
    DashboardPage dashboardPage;

    @BeforeEach
    void browserSetup() {
        var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        startBalanceFirstCard = dashboardPage.getFirstCardBalance();
        startBalanceSecondCard = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyToFirstCardFromSecondCardTest() {
        amount = 1;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceFirstCard + amount, finalBalanceFirstCard);
        assertEquals(startBalanceSecondCard - amount, finalBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToFirstCardFromSecondCardV2Test() {
        amount = 0;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceFirstCard, finalBalanceFirstCard);
        assertEquals(startBalanceSecondCard, finalBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToFirstCardFromSecondCardV3Test() {
        amount = startBalanceSecondCard - 1;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(1, finalBalanceSecondCard);
        assertEquals(startBalanceFirstCard + amount, finalBalanceFirstCard);
    }

    @Test
    void shouldTransferMoneyToFirstCardFromSecondCardV4Test() {
        amount = startBalanceSecondCard;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(0, finalBalanceSecondCard);
        assertEquals(startBalanceFirstCard + amount, finalBalanceFirstCard);
    }

    @Test
    void shouldGetErrorIfAmountAboveCurrentBalanceTest() {
        amount = startBalanceSecondCard + 1;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        topUpPage.unsuccessfullMoneyTransfer(Integer.toString(amount), cardNumber);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardTest() {
        amount = 1;
        var topUpPage = dashboardPage.topUpSecondCard();
        var cardNumber = DataHelper.CardNumber.getFirstCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceSecondCard + amount, finalBalanceSecondCard);
        assertEquals(startBalanceFirstCard - amount, finalBalanceFirstCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardV2Test() {
        amount = 0;
        var topUpPage = dashboardPage.topUpSecondCard();
        var cardNumber = DataHelper.CardNumber.getFirstCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceSecondCard, finalBalanceSecondCard);
        assertEquals(startBalanceFirstCard, finalBalanceFirstCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardV3Test() {
        amount = startBalanceFirstCard - 1;
        var topUpPage = dashboardPage.topUpSecondCard();
        var cardNumber = DataHelper.CardNumber.getFirstCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(1, finalBalanceFirstCard);
        assertEquals(startBalanceSecondCard + amount, finalBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyToSecondCardFromFirstCardV4Test() {
        amount = startBalanceFirstCard;
        var topUpPage = dashboardPage.topUpSecondCard();
        var cardNumber = DataHelper.CardNumber.getFirstCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(0, finalBalanceFirstCard);
        assertEquals(startBalanceSecondCard + amount, finalBalanceSecondCard);
    }

    @Test
    void shouldNotAllowNegativeAmountTest() {
        amount = -1;
        var topUpPage = dashboardPage.topUpFirstCard();
        var cardNumber = DataHelper.CardNumber.getSecondCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceFirstCard - amount, finalBalanceFirstCard);
        assertEquals(startBalanceSecondCard + amount, finalBalanceSecondCard);
    }

    @Test
    void shouldNotAllowNegativeAmountV2Test() {
        amount = -1;
        var topUpPage = dashboardPage.topUpSecondCard();
        var cardNumber = DataHelper.CardNumber.getFirstCardNumber().getCardNumber();
        var returnPage = topUpPage.successfullMoneyTransfer(Integer.toString(amount), cardNumber);
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceSecondCard - amount, finalBalanceSecondCard);
        assertEquals(startBalanceFirstCard + amount, finalBalanceFirstCard);
    }

    @Test
    void shouldCancelTransactionTest() {
        var topUpPage = dashboardPage.topUpFirstCard();
        MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
        var cancelTransaction = moneyTransferPage.cancelTransfer();
        int finalBalanceFirstCard = dashboardPage.getFirstCardBalance();
        int finalBalanceSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(startBalanceFirstCard, finalBalanceFirstCard);
        assertEquals(startBalanceSecondCard, finalBalanceSecondCard);
    }
}