package ru.netology.delivery.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");

    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").doubleClick().setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement'] span").click();
        $x("//*[contains(text(), 'Запланировать')]").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").doubleClick().setValue(secondMeetingDate);
        $x("//*[contains(text(), 'Запланировать')]").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible)
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(By.className("button")).click();
        $x("//span[contains(text(), 'Перепланировать')]").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }

}