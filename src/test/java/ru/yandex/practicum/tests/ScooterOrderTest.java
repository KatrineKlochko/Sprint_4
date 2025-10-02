package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.OrderPage;

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

    @Parameterized.Parameters(name = "Заказ: {1} {2}, метро: {4}, срок: {8}, цвет: {9}")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {MainPage.ORDER_BUTTON_TOP, "Вася", "Пупкин", "ул. Ленина, д. 10", "Лубянка", "+79261234567", OrderPage.NEXT_BUTTON, 1, "трое суток", "чёрный жемчуг", "Позвоните при доставке"},
                {MainPage.ORDER_BUTTON_HERO, "Петя", "Петров", "пр-т Мира, д. 25", "Лихоборы", "+79876543210", OrderPage.NEXT_BUTTON, 2, "сутки", "серая безысходность", "Оставьте у двери"}
        });
    }

    @Test
    public void scooterOrderBottomTop(){

        WebDriver driver = factory.getDriver();
        WebDriverWait wait = factory.getWait();

        MainPage mainPage = new MainPage(driver, wait);
        OrderPage orderPage = new OrderPage(driver, wait);


        mainPage.clickOnOrderButton(orderButton);

        orderPage.fillOrderForm(firstName, lastName, address, metro, phone, nextButton);
        orderPage.fillOrderFormOptions(daysToDelivery, rentPeriod, color, comment);

        orderPage.confirmOrderWindow();
        orderPage.clickOnOrderButtonYes();

        WebElement statusButton = orderPage.waitForModalWindow();
        assertTrue("Модальное окно с подтверждением заказа не отображается", statusButton.isDisplayed());
        orderPage.clickViewStatusButton();

    }
}
