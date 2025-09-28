package ru.yandex.practicum;
import org.openqa.selenium.By;

public class MainPage {

    // Кнопка «Заказать» (в шапке)
    public static final By orderButtonTop = By.className("Button_Button__ra12g");

    // Кнопка «Заказать» (под заголовком)
    public static final By orderButtonHero = By.className("Button_Middle__1CSJM");

    // Кнопка «да все привыкли» (принять куки)
    public static final By cookieButton = By.className("App_CookieButton__3cvqF");

    // Метод для вопроса по индексу
    public static By getQuestion(int index) {
        return By.id("accordion__heading-" + index);
    }

    // Метод для ответа по индексу
    public static By getAnswer(int index) {
        return By.id("accordion__panel-" + index);
    }

}
