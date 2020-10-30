package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static testData.TestData.PASSWORD;
import static testData.TestData.USERNAME_1;

public class LoginTest extends BaseTest {

    @Test
    public void isSuccessfulAuthorization() {
        loginPage
                .openPage()
                .isPageOpen()
                .successfulLogIn(USERNAME_1, System.getProperty("password"));

        assertEquals(productsPage.getNameOfPage(), "Products", "Is not page Products");
    }

    @Test(dataProvider = "Data fo test login")
    public void displayErrorMessage(String username, String password, String expectedResult) {
        loginPage
                .openPage()
                .isPageOpen()
                .failedLogIn(username, password);

        assertEquals(loginPage.getErrorMessage(), expectedResult, "Don't display error message");
    }

    @DataProvider(name = "Data fo test login")
    public Object[][] dataForTestLogin() {
        return new Object[][]{
                {"", PASSWORD, "Epic sadface: Username is required"},
                {USERNAME_1, "", "Epic sadface: Password is required"},
                {USERNAME_1, "12345", "Epic sadface: Username and password do not match any user in this service"}
        };
    }
}
