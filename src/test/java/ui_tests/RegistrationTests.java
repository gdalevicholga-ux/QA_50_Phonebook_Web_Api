package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactPage;
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
        User user = new User("olya"+i+"@gmail.com", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationPositiveTest_WithFaker(){
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextInContactPageMessagePresent("No Contacts here!"));
    }
    @Test
    public void registrationNegativeTest_WrongEmailFormat(){
        User user = new User("wrongemailformat", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_EmptyEmail(){
        User user = new User("", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_EmptyPassword(){
        User user = new User(positiveUser().getUsername(), "");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_EmptyFields(){
        User user = new User("", "");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_PasswordWithoutUpperCase(){
        User user = new User(positiveUser().getUsername(), "password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_PasswordWithoutLowerCase(){
        User user = new User(positiveUser().getUsername(), "PASSWORD123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_PasswordWithoutDigit(){
        User user = new User(positiveUser().getUsername(), "Password!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_PasswordWithoutSpecialChar(){
        User user = new User(positiveUser().getUsername(), "Password123");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_ShortPassword(){
        User user = new User(positiveUser().getUsername(), "Pass1!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_EmailWithoutAtSymbol(){
        User user = new User("margotestgmail.com", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_EmailWithoutDomain(){
        User user = new User("margotest@", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password format");
    }

    @Test
    public void registrationNegativeTest_DuplicateUser(){
        User user = new User("margotest@gmail.com", "Password123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "User already exist");
    }
}
