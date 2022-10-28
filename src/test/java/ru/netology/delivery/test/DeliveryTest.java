package ru.netology.delivery.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DeliveryInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.delivery.data.DataGenerator.Registration.generateInfo;

class DeliveryTest {

    @BeforeEach
    void preliminaryWork() {
        open("http://localhost:9999");
        $x(".//span[@data-test-id='date']//child::input[@placeholder]")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @Test
    @DisplayName("Should successfully plan meeting and change its date")
    void shouldSuccessfullyPlanMeetingAndChangeDate() {
        DeliveryInfo firstDate = generateInfo(3);
        DeliveryInfo secondDate = generateInfo(15);
        $x(".//span[@data-test-id='city']//child::input[@placeholder]").val(firstDate.getCity());
        $x(".//span[@data-test-id='date']//child::input[@placeholder]").val(firstDate.getDate());
        $x(".//span[@data-test-id='name']//child::input").val(firstDate.getName());
        $x(".//span[@data-test-id='phone']//child::input").val(firstDate.getPhoneNumber());
        $x(".//label[@data-test-id='agreement']").click();
        $x(".//button[@role='button']//child::span[contains(text(), 'Запланировать')]").click();
        $x(".//div[@data-test-id='success-notification']/child::div[@class='notification__title']").
                shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
        $x(".//div[@data-test-id='success-notification']/child::div[@class='notification__content']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + firstDate.getDate()));
        $x(".//span[@data-test-id='date']//child::input[@placeholder]")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x(".//span[@data-test-id='date']//child::input").val(secondDate.getDate());
        $x(".//button[@role='button']//child::span[contains(text(), 'Запланировать')]").click();

        $x(".//div[@data-test-id='replan-notification']/child::" +
                "div[contains(text(), 'Необходимо подтверждение')]")
                .shouldBe(visible, Duration.ofSeconds(15));
        $x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        $x(".//div[@data-test-id='success-notification']/child::div[@class='notification__content']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + secondDate.getDate()));
    }

    @Test
    @DisplayName("Should successfully plan meeting and try to repeat to send the form with the same data")
    void shouldSuccessfullyPlanMeetingAndRepeatSameDate() {
        DeliveryInfo firstDate = generateInfo(3);
        Configuration.holdBrowserOpen = true;
        $x(".//span[@data-test-id='city']//child::input[@placeholder]").val(firstDate.getCity());
        $x(".//span[@data-test-id='date']//child::input[@placeholder]").val(firstDate.getDate());
        $x(".//span[@data-test-id='name']//child::input").val(firstDate.getName());
        $x(".//span[@data-test-id='phone']//child::input").val(firstDate.getPhoneNumber());
        $x(".//label[@data-test-id='agreement']").click();
        $x(".//button[@role='button']//child::span[contains(text(), 'Запланировать')]").click();
        $x(".//div[@data-test-id='success-notification']/child::div[@class='notification__title']").
                shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
        $x(".//div[@data-test-id='success-notification']/child::div[@class='notification__content']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + firstDate.getDate()));
        $x(".//span[@data-test-id='date']//child::input[@placeholder]")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x(".//span[@data-test-id='date']//child::input").val(firstDate.getDate());
        $x(".//button[@role='button']//child::span[contains(text(), 'Запланировать')]").click();

        $x(".//div[@data-test-id='replan-notification']/child::" +
                "div[contains(text(), 'Необходимо подтверждение')]")
                .shouldBe(visible, Duration.ofSeconds(15));
        $x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        $x(".//div[@data-test-id='success-notification']/child::div[@class='notification__content']")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + firstDate.getDate()));
    }

}
