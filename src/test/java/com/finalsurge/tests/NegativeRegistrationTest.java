package com.finalsurge.tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class NegativeRegistrationTest extends BaseTest {

    @Test
    public void testOpenRegistrationPage() {
        System.out.println("Тест 1: Открываем страницу регистрации");
        open("/register.cshtml");

        // Проверяем что страница содержит нужный текст
        $(By.tagName("body")).shouldHave(Condition.partialText("Create a New Account"));
        System.out.println("✅ Страница регистрации открыта успешно!");
    }

    @Test
    public void testCheckRegistrationForm() {
        System.out.println("Тест 2: Проверяем форму регистрации");
        open("/register.cshtml");

        // Проверяем наличие всех полей формы
        $(By.id("create_first")).shouldBe(Condition.visible);
        $(By.id("create_last")).shouldBe(Condition.visible);
        $(By.id("create_email")).shouldBe(Condition.visible);
        $(By.id("password_meter")).shouldBe(Condition.visible);
        $(By.id("create_passwordmatch")).shouldBe(Condition.visible);

        System.out.println("✅ Все элементы формы регистрации найдены!");
    }

    @Test
    public void testEmptyEmailRegistration() {
        System.out.println("Тест 3: Регистрация с пустым email");
        open("/register.cshtml");

        // Заполняем только обязательные поля, кроме email
        $(By.id("create_first")).setValue("Test");
        $(By.id("create_last")).setValue("User");
        $(By.id("password_meter")).setValue("Password123!");
        $(By.id("create_passwordmatch")).setValue("Password123!");

        // Нажимаем кнопку регистрации
        $(By.xpath("//button[@type='submit']")).click();

        // Ждем немного для появления ошибки
        sleep(1000);

        // Проверяем что email поле показывает ошибку
        $(By.id("create_email")).shouldHave(Condition.cssClass("error"));

        System.out.println("✅ Ошибка при пустом email показана!");
    }

    @Test
    public void testInvalidEmailFormat() {
        System.out.println("Тест 4: Неверный формат email");
        open("/register.cshtml");

        // Вводим невалидный email
        $(By.id("create_first")).setValue("Test");
        $(By.id("create_last")).setValue("User");
        $(By.id("create_email")).setValue("not-an-email");
        $(By.id("password_meter")).setValue("Password123!");
        $(By.id("create_passwordmatch")).setValue("Password123!");

        $(By.xpath("//button[@type='submit']")).click();

        // Ждем
        sleep(1000);

        // Проверяем ошибку email
        $(By.id("create_email")).shouldHave(Condition.cssClass("error"));

        System.out.println("✅ Ошибка невалидного email показана!");
    }

    @Test
    public void testPasswordMismatch() {
        System.out.println("Тест 5: Несовпадающие пароли");
        open("/register.cshtml");

        // Заполняем форму с разными паролями
        $(By.id("create_first")).setValue("Test");
        $(By.id("create_last")).setValue("User");
        $(By.id("create_email")).setValue("test@example.com");
        $(By.id("password_meter")).setValue("Password123!");
        $(By.id("create_passwordmatch")).setValue("DifferentPassword456!");

        $(By.xpath("//button[@type='submit']")).click();

        // Ждем
        sleep(1000);

        // Проверяем что остались на странице регистрации (не прошли)
        $(By.tagName("body")).shouldHave(Condition.partialText("Create a New Account"));

        System.out.println("✅ Ошибка несовпадения паролей показана!");
    }

    @Test
    public void testEmptyFirstName() {
        System.out.println("Тест 6: Пустое поле имени");
        open("/register.cshtml");

        // Оставляем пустое имя
        $(By.id("create_last")).setValue("User");
        $(By.id("create_email")).setValue("test@example.com");
        $(By.id("password_meter")).setValue("Password123!");
        $(By.id("create_passwordmatch")).setValue("Password123!");

        $(By.xpath("//button[@type='submit']")).click();

        // Ждем
        sleep(1000);

        // Проверяем ошибку
        $(By.id("create_first")).shouldHave(Condition.cssClass("error"));

        System.out.println("✅ Ошибка пустого имени показана!");
    }
}