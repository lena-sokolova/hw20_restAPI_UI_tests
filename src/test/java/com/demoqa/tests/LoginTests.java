package com.demoqa.tests;

import com.demoqa.api.AuthorizationApi;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.demoqa.tests.TestData.credentials;

public class LoginTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    AuthorizationApi authorizationApi = new AuthorizationApi();

    @Test
    void successfulLoginTest() {

        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        profilePage.openProfileWithCookies(loginResponse);

        open("/profile");
        $("#userName-value").shouldHave(text(credentials.getUserName()));
    }
}