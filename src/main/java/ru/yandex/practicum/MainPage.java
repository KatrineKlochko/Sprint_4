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
    public static final String ORDER_BUTTON_TOP_CLASS_NAME = "Button_Button__ra12g";

    // Кнопка «Заказать» (под заголовком)
    public static final String ORDER_BUTTON_HERO_CLASS_NAME = "Button_Middle__1CSJM";

    // Кнопка «да все привыкли» (принять куки)
    public static final String COOKIE_BUTTON_CLASS_NAME = "App_CookieButton__3cvqF";

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
    public void clickOnOrderButton(By orderButton) {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

}
