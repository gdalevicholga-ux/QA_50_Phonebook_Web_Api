package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyser;

import static utils.PropertiesReader.*;


import static utils.UserFactory.positiveUser;

public class LoginTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest(){
        // System.out.println("first test");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
     //   loginPage.typeLoginRegistrationForm("olya207@gmail.com",
      //          "Password123!");
        loginPage.typeLoginRegistrationForm(getProperty("base.properties","login"),
                getProperty("base.properties","password"));
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactPage(getDriver())
                .isTextInBtnAddPresent("ADD"));
    }

    @Test
    public void loginPositiveTestWithUser(){
        User user = new User(getProperty("base.properties","login"),
                getProperty("base.properties","password"));
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
    }

    @Test
    public void loginNegativeTest_WrongEmail(){
        User user = new User("olya207gmail.com", "Password123!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
    @Test
    public void loginNegativeTest_WrongPassword() {
        User user = new User("olya207gmail.com", "WrongPassword123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_EmptyEmail(){
        User user = new User("", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_EmptyPassword(){
        User user = new User("olya207gmail.com", "");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_EmptyFields(){
        User user = new User("", "");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_UnregisteredUser(){
        User user = positiveUser();
        System.out.println("Trying to login with unregistered user: " + user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_EmailWithoutDomain(){
        User user = new User("olya207@", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

}