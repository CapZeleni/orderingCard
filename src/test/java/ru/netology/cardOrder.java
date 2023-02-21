package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;


class cardOrder {
    @Test
    void cardOrderFieldsTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Калуга");

        SelenideElement dateElement = $("[data-test-id=date] input[class=input__control]");
        dateElement.doubleClick().sendKeys(Keys.BACK_SPACE);
        String dateMeeting = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        dateElement.setValue(dateMeeting);

        $("[data-test-id=name] input").setValue("Рулькин Адольф");
        $("[data-test-id=phone] input").setValue("+79190336633");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification] .notification__content")
                .waitUntil(visible, 15000)
                .shouldHave(exactText("Встреча успешно забронирована на " + dateMeeting));
        
    }
}
