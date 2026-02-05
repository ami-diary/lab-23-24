package com.finalsurge.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        System.out.println("=== НАСТРОЙКА ТЕСТА ===");

        // Используем системные свойства или значения по умолчанию
        String browser = System.getProperty("selenide.browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Конфигурация Selenide
        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 15000;  // 15 секунд
        Configuration.browserSize = "1920x1080";

        System.out.println("Браузер: " + browser);
        System.out.println("Headless: " + headless);
        System.out.println("URL: " + Configuration.baseUrl);
        System.out.println("=========================");
    }
}