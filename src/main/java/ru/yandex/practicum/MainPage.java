package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Кнопка «Заказать» (в шапке)
    private static final By ORDER_BUTTON_TOP = By.className("Button_Button__ra12g");

    // Кнопка «Заказать» (под заголовком)
    private static final By ORDER_BUTTON_HERO = By.className("Button_Middle__1CSJM");

    // Кнопка «да все привыкли» (принять куки)
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");

    // Локатор для вопросов
    private static final String QUESTION_ELEMENT_ID = "accordion__heading-";

    // Локатор для ответов
    private static final String ANSWER_ELEMENT_ID = "accordion__panel-";


    // Метод для клика по вопросу
    public void clickQuestion(int index) {
        By questionLocator = By.id(QUESTION_ELEMENT_ID + index);
        WebElement questionElement = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        questionElement.click();
    }

    //Метод проверки текста ответа
    public String getAnswerText(int index) {
        By answerLocator = By.id(ANSWER_ELEMENT_ID + index);
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answerElement.getText().trim();
    }

    //Метод для клика на кнопку заказать
    public void clickOrderButton(String buttonType) {
        By locator;
        switch (buttonType) {
            case "top":
                locator = ORDER_BUTTON_TOP;
                break;
            case "hero":
                locator = ORDER_BUTTON_HERO;
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип кнопки: " + buttonType);
        }
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    //Метод клик на кнопку куки
    public void clickCookieButton() {
        wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
    }
}
