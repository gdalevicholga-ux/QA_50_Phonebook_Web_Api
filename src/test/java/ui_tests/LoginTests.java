package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    @Test
    public void loginPositiveTest(){
        System.out.println("first test");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm("bolik@gmail.com",
                "Bolik12345!");
        loginPage.clickBtnLoginForm();

        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isBtnAddDisplayed());
       // Assert.assertTrue(new ContactsPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
    }

    @Test
    public void loginPositiveTestWithUser(){
        User user = new User("bolik@gmail.com",
                "Bolik12345!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isBtnContactsDisplayed());
    }
    @Test
    public void loginNegativeTest_WrongEmail(){
        User user = new User("bolikgmail.com","Bolik12345!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),"Wrong email or password");

    }
}