package ru.netology.delivery.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.DeliveryInfo;

import static com.codeborne.selenide.Selenide.*;
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
        Configuration.holdBrowserOpen = true;
        $x(".//span[@data-test-id='city']//child::input[@placeholder]").val(firstDate.getCity());
        $x(".//span[@data-test-id='date']//child::input[@placeholder]").val(firstDate.getDate());
        $x(".//span[@data-test-id='name']//child::input").val(firstDate.getName());
        $x(".//span[@data-test-id='phone']//child::input").val(firstDate.getPhoneNumber());
        $x(".//label[@data-test-id='agreement']").click();

    }
}
