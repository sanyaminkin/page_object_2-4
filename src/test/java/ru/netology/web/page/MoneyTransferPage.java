package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement transactionAmountField = $("[data-test-id='amount'] input");
    private SelenideElement transactionFromCardField = $("[data-test-id='from'] input");
    private SelenideElement transactionToCardField = $("[data-test-id='to']");
    private SelenideElement topUpActionButton = $("[data-test-id='action-transfer']");
    private SelenideElement topUpCancelButton = $("[data-test-id='action-cancel']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public MoneyTransferPage() {
        $("[class = 'heading heading_size_xl heading_theme_alfa-on-white']").shouldBe(visible).shouldHave(exactText("Пополнение карты"));
    }

    private void fillAmountAndCardNumberField (String amount, String cardNumber) {
        transactionAmountField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.DELETE));
        transactionAmountField.setValue(amount);
        transactionFromCardField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.DELETE));
        transactionFromCardField.setValue(cardNumber);
        topUpActionButton.click();
    }

    public DashboardPage successfullMoneyTransfer(String amount, String cardNumber){
        fillAmountAndCardNumberField(amount, cardNumber);
        return new DashboardPage();
    }

    public void unsuccessfullMoneyTransfer(String amount, String cardNumber) {
        fillAmountAndCardNumberField(amount, cardNumber);
        errorNotification.shouldBe(visible);
    }

    public DashboardPage cancelTransfer() {
        topUpCancelButton.click();
        return new DashboardPage();
    }
}
