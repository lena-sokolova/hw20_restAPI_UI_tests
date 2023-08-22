package com.demoqa.pages;

import com.demoqa.models.LoginResponseModel;
import com.demoqa.tests.TestBase;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage extends TestBase {

    public ProfilePage openProfileWithCookies (LoginResponseModel loginResponseModel){

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponseModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponseModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponseModel.getExpires()));
        return this;
    }
}
