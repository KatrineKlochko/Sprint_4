package ru.yandex.practicum;

import org.openqa.selenium.By;

public class OrderPage {

    //кнопка далее
    public static final By nextButton = By.className("Button_Middle__1CSJM");

    //поля формы заказа
    public static final By firstNameField = By.cssSelector("input[placeholder='* Имя']");
    public static final By lastNameField = By.cssSelector("input[placeholder='* Фамилия']");
    public static final By addressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    public static final By metroField = By.cssSelector("input[placeholder='* Станция метро']");
    public static final By phoneField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    //поля формы заказа самоката (про аренду)
    public static final By dateField = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    public static final By rentField = By.className("Dropdown-placeholder");
    public static final By color = By.xpath(".//div[@class='Order_Title__3EKne' and text()='Цвет самоката']");
    public static final By commentField = By.cssSelector("input[placeholder='Комментарий для курьера']");

    //кнопка заказать
    public static final By orderButtonFinal = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

    //окно подтверждения заказа
    public static final By orderConfirmationWindow = By.className("Order_Modal__YZ-d3");

    //кнопка подтверждения заказа (да)
    public static final By orderButtonYes = By.xpath("//button[text()='Да']");

    //модальное окно
    public static final By modalWindow = By.className("Order_NextButton__1_rCA");
    public static final By viewStatusButton = By.xpath("//button[text()='Посмотреть статус']");



    // Метод для выбора конкретного дня месяца
    public static By getDeliveryDateOption(int dayOfMonth) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + dayOfMonth + "']");
    }

    // Метод для выбора срока аренды
    public static By getRentPeriodOption(String rentPeriod) {
        return By.xpath("//div[@class='Dropdown-option' and text()='" + rentPeriod + "']");
    }

    //метод для выбора цвета самоката
    public static By getColorOption(String color) {
        return By.xpath("//label[contains(@class,'Checkbox_Label') and normalize-space(text())='" + color + "']");
    }

}
