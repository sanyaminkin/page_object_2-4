package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement topUpFirstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']")
            .$("[data-test-id='action-deposit']");
    private SelenideElement topUpSecondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']")
            .$("[data-test-id='action-deposit']");
    private SelenideElement firstCardBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement reloadButton = $("[data-test-id='action-reload']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public MoneyTransferPage topUpFirstCard() {
        topUpFirstCardButton.click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage topUpSecondCard() {
        topUpSecondCardButton.click();
        return new MoneyTransferPage();
    }

    public DashboardPage reloadPage() {
        reloadButton.click();
        return new DashboardPage();
    }

    public int getFirstCardBalance() {
        String [] innerText = firstCardBalance.innerText().split(" ");
        int cardBalance = Integer.parseInt(innerText[5]);
        return cardBalance;
    }

    public int getSecondCardBalance() {
        String [] innerText = secondCardBalance.innerText().split(" ");
        int cardBalance = Integer.parseInt(innerText[5]);
        return cardBalance;
    }
}
