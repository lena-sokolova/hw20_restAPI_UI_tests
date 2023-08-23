package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
}
