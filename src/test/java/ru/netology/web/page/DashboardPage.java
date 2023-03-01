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
    private SelenideElement reloadButton = $("[data-test-id='action-reload']");
    private SelenideElement transactionAmountField = $("[data-test-id='amount'] input");
    private SelenideElement transactionFromCardField = $("[data-test-id='from'] input");
    private SelenideElement transactionToCardField = $("[data-test-id='to']");
    private SelenideElement topUpActionButton = $("[data-test-id='action-transfer']");
    private SelenideElement topUpCancelButton = $("[data-test-id='action-cancel']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }
}
