package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;
import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        User user = new User("masha"+i+"@gmail.com", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationPositiveTest_WithFaker(){
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactsPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }
}
