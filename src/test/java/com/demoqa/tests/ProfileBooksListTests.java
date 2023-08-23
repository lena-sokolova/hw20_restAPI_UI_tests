package com.demoqa.tests;

import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.demoqa.tests.TestData.credentials;

public class ProfileBooksListTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();

    @Test
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);
        profilePage.openProfileWithCookies(loginResponse);

        open("/profile");
        $("[id='see-book-Git Pocket Guide']").shouldBe(visible);
    }

    @Test
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);
        booksApi.deleteBook(loginResponse,
                new DeleteBookModel("9781449325862", loginResponse.getUserId()));
        profilePage.openProfileWithCookies(loginResponse);

        open("/profile");
        $("[id='see-book-Git Pocket Guide']").shouldNot(visible);
    }
}
