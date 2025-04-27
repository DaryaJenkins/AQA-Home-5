package ru.netology.aqa.delivery;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        String city = DataGenerator.generateCity();
        String name = DataGenerator.generateFullName();
        String phone = DataGenerator.generatePhone();
        String firstMeetingDate = DataGenerator.generateDate(4, "dd.MM.yyyy");
        String secondMeetingDate = DataGenerator.generateDate(7, "dd.MM.yyyy");

        $("[data-test-id=city] input").setValue(city);
        $$("[data-test-id='date'] input").findBy(visible)
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='success-notification']")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(5));

        $$("[data-test-id='date'] input").findBy(visible)
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $("button.button").click();
        $("[data-test-id='replan-notification']")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification']")
                .shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(5));
    }
}