package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.OrderPage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class ScooterOrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final By orderButton;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final By nextButton;
    private final int daysToDelivery;
    private final String rentPeriod;
    private final String color;
    private final String comment;

    public ScooterOrderTest(By orderButton, String firstName, String lastName, String address, String metro, String phone, By nextButton, int daysToDelivery, String rentPeriod, String color, String comment) {
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.nextButton = nextButton;
        this.daysToDelivery = daysToDelivery;
        this.rentPeriod = rentPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {MainPage.orderButtonTop, "Вася", "Пупкин", "ул. Ленина, д. 10", "Лубянка", "+79261234567", OrderPage.nextButton, 1, "трое суток", "чёрный жемчуг", "Позвоните при доставке"},
                {MainPage.orderButtonHero, "Петя", "Петров", "пр-т Мира, д. 25", "Лихоборы", "+79876543210", OrderPage.nextButton, 2, "сутки", "серая безысходность", "Оставьте у двери"}
        });
    }

    @Test
    public void scooterOrderBottomTop(){

        WebDriver driver = factory.getDriver();
        WebDriverWait wait = factory.getWait();

        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();

        driver.findElement(OrderPage.firstNameField).sendKeys(firstName);
        driver.findElement(OrderPage.lastNameField).sendKeys(lastName);
        driver.findElement(OrderPage.addressField).sendKeys(address);

        WebElement metroField = wait.until(ExpectedConditions.elementToBeClickable(OrderPage.metroField));
        metroField.sendKeys(metro);

        By metroSelector = By.className("select-search__select");
        WebElement station = wait.until(ExpectedConditions.elementToBeClickable(metroSelector));
        station.click();

        driver.findElement(OrderPage.phoneField).sendKeys(phone);

        driver.findElement(nextButton).click();

        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(OrderPage.dateField));
        dateField.click();
        LocalDate deliveryDate = LocalDate.now().plusDays(daysToDelivery);
        int dayOfMonth = deliveryDate.getDayOfMonth();
        By dayLocator = OrderPage.getDeliveryDateOption(dayOfMonth);
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dayElement.click();

        WebElement rentField = wait.until(ExpectedConditions.elementToBeClickable(OrderPage.rentField));
        rentField.click();
        By rentOption = OrderPage.getRentPeriodOption(rentPeriod);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(rentOption));
        option.click();

        By colorOption = OrderPage.getColorOption(color);
        WebElement colorElement = wait.until(ExpectedConditions.elementToBeClickable(colorOption));
        colorElement.click();

        driver.findElement(OrderPage.commentField).sendKeys(comment);

        driver.findElement(OrderPage.orderButtonFinal).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(OrderPage.orderConfirmationWindow));
        wait.until(ExpectedConditions.elementToBeClickable(OrderPage.orderButtonYes)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(OrderPage.modalWindow));
        WebElement statusButton = wait.until(ExpectedConditions.elementToBeClickable(OrderPage.viewStatusButton));
        assertTrue("Модальное окно с подтверждением заказа не отображается", statusButton.isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(OrderPage.viewStatusButton)).click();

    }
}
