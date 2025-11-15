package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //кнопка далее
    public static final By NEXT_BUTTON = By.className("Button_Middle__1CSJM");

    //поля формы заказа
    private static final By FIRST_NAME_FIELD = By.cssSelector("input[placeholder='* Имя']");
    private static final By LAST_NAME_FIELD = By.cssSelector("input[placeholder='* Фамилия']");
    private static final By ADDRESS_FIELD = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private static final By METRO_FIELD = By.cssSelector("input[placeholder='* Станция метро']");
    private static final By PHONE_FIELD = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    private static final By METRO_SELECTOR = By.className("select-search__select");

    //поля формы заказа самоката (про аренду)
    private static final By DATE_FIELD = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    private static final By RENT_FIELD = By.className("Dropdown-placeholder");
    private static final By COLOR = By.xpath(".//div[@class='Order_Title__3EKne' and text()='Цвет самоката']");
    private static final By COMMENT_FIELD = By.cssSelector("input[placeholder='Комментарий для курьера']");

    //кнопка заказать
    private static final By ORDER_BUTTON_FINAL = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

    //окно подтверждения заказа
    private static final By ORDER_CONFIRMATION_WINDOW = By.className("Order_Modal__YZ-d3");

    //кнопка подтверждения заказа (да)
    private static final By ORDER_BUTTON_YES = By.xpath("//button[text()='Да']");

    //модальное окно
    private static final By MODAL_WINDOW = By.className("Order_NextButton__1_rCA");
    private static final By VIEW_STATUS_BUTTON = By.xpath("//button[text()='Посмотреть статус']");

    // Выбор конкретного дня месяца
    private static final String DELIVERY_DATE_OPTION_XPATH = "//div[contains(@class,'react-datepicker__day') and text()='%s']";

    // Выбор срока аренды
    private static final String RENT_PERIOD_OPTION_XPATH = "//div[@class='Dropdown-option' and text()='%s']";

    // Выбор цвета самоката
    private static final String COLOR_OPTION_XPATH = "//label[contains(@class,'Checkbox_Label') and normalize-space(text())='%s']";



    // Метод поля имя
    public void fillFirstName(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
    }

    //Метод поля фамилия
    public void fillLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
    }

    //Метод поля адрес
    public void fillAddress(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }

    //Метод поля метро
    public void selectMetro(String metro) {
        WebElement metroField = wait.until(ExpectedConditions.elementToBeClickable(METRO_FIELD));
        metroField.sendKeys(metro);
        WebElement station = wait.until(ExpectedConditions.elementToBeClickable(METRO_SELECTOR));
        station.click();
    }

    //Метод поля телефон
    public void fillPhone(String phone) {
        driver.findElement(PHONE_FIELD).sendKeys(phone);
    }

    //Метод клик на кнопку далее
    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON)).click();
    }

    //Метод выбора даты доставки
    public void selectDeliveryDate(int daysToDelivery) {
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(DATE_FIELD));
        dateField.click();
        LocalDate deliveryDate = LocalDate.now().plusDays(daysToDelivery);
        int dayOfMonth = deliveryDate.getDayOfMonth();
        By dayLocator = By.xpath(String.format(DELIVERY_DATE_OPTION_XPATH, dayOfMonth));
        wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();
    }

    //Метод выбора срока аренды
    public void selectRentPeriod(String rentPeriod) {
        WebElement rentField = wait.until(ExpectedConditions.elementToBeClickable(RENT_FIELD));
        rentField.click();
        By rentOption = By.xpath(String.format(RENT_PERIOD_OPTION_XPATH, rentPeriod));
        wait.until(ExpectedConditions.elementToBeClickable(rentOption)).click();
    }

    //Метод выбора цвета самоката
    public void selectColor(String color) {
        By colorOption = By.xpath(String.format(COLOR_OPTION_XPATH, color));
        wait.until(ExpectedConditions.elementToBeClickable(colorOption)).click();
    }

    //Метод поля комментарий
    public void addComment(String comment) {
        driver.findElement(COMMENT_FIELD).sendKeys(comment);
    }

    //Метод клик на кнопку заказать финальную
    public void clickFinalOrderButton() {
        driver.findElement(ORDER_BUTTON_FINAL).click();
    }

    //Метод ожидания окна подтверждения заказа
    public void confirmOrderWindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_CONFIRMATION_WINDOW));
    }

    //Метод клик на кнопку ДА в окне подтверждения заказа
    public void clickOnOrderButtonYes() {
        wait.until(ExpectedConditions.elementToBeClickable(ORDER_BUTTON_YES)).click();
    }

    //Метод ожидания модального окна
    public WebElement waitForModalWindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_WINDOW));
        return wait.until(ExpectedConditions.elementToBeClickable(VIEW_STATUS_BUTTON));
    }

    //Метод клик на кнопку посмотреть статус заказа
    public void clickViewStatusButton() {
        wait.until(ExpectedConditions.elementToBeClickable(VIEW_STATUS_BUTTON)).click();
    }

    //Метод заполнение формы заказа
    public void fillOrderForm(String firstName, String lastName, String address, String metro, String phone) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillAddress(address);
        selectMetro(metro);
        fillPhone(phone);
        clickNextButton();
    }

    //Метод заполнение опций формы заказа (доставка, цвет и тд)
    public void fillOrderFormOptions(int daysToDelivery, String rentPeriod, String color, String comment) {
        selectDeliveryDate(daysToDelivery);
        selectRentPeriod(rentPeriod);
        selectColor(color);
        addComment(comment);
        clickFinalOrderButton();
    }

}
