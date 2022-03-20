package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.commonPage;
import pageObjects.signupPage;

public class signup {
    //This class maintains all the step definitions related to signup flow
    commonPage pg_common = new commonPage();
    signupPage pg_signup = new signupPage();

    @Then("user enters the given details {string},{string},{string},{string}")
    public void userEntersTheGivenDetails(String firstName, String lastName, String email, String password) {
        //Fill the signup page
        pg_common.verifyLoginButtonIsAvailable();
        pg_common.scrollDownThePage("Sign up now");
        pg_signup.clickOnSignupNowLink();
        pg_common.scrollDownThePage("Create account");
        pg_signup.enterFirstName(firstName);
        pg_signup.enterLastName(lastName);
        pg_signup.enterEmail(email);
        pg_signup.enterPassword(password);
        pg_signup.enterConfirmPassword(password);
    }

    @Then("user agrees to Terms and conditions")
    public void userAgreesToTermsAndConditions() {
        pg_signup.selectTermsAndConditionsCheckbox();
    }

    @Then("user click on Create Account button")
    public void userClickOnCreateAccountButton() {
        pg_signup.clickOnCreateAccountButton();
    }
}
