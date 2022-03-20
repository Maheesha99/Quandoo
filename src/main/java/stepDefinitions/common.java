package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.commonPage;

public class common {
    //This class maintains all the step definitions which are common for the entire application

    commonPage pg_common = new commonPage();

    @And("user landed on to the home page and verify {string} label")
    public void userLandedOnToTheHomePageAndVerifyLabel(String label) {
        pg_common.verifyTheLabelInHomePage(label);
    }

    @And("user accept the device location Alert if available")
    public void userAcceptTheDeviceLocationAlertIfAvailable() {
        pg_common.clickOnAllowOnlyWhileUsingTheAppButton();
    }

    @Then("user click on Hamburger menu")
    public void userClickOnHamburgerMenu() {
        pg_common.clickOnHamburgerMenu();
    }

    @Then("user click on Log in or Sign up option")
    public void userClickOnLogInOrSignUpOption() {
        pg_common.clickOnLoginOrSignup();
    }

    @Then("user search a {string} restaurant in {string}")
    public void userSearchARestaurantIn(String cuisine, String location) {
        pg_common.searchForARestaurant(cuisine, location);
    }

    @Then("user filter {string} from the result list")
    public void userFilterFromTheResultList(String filter) {
        pg_common.userSelectAFilter(filter);
    }

    @Then("user select {string} from the results list")
    public void userSelectFromTheResultsList(String itemNumber) {
        pg_common.userSelectARestaurantFromTheResults(itemNumber);
    }

    @Then("user logout from the application")
    public void userLogoutFromTheApplication() {
        pg_common.clickOnLogout();
    }

    @Then("user go back from the simple search view")
    public void userGoBackFromTheSimpleSearchView() {
        pg_common.goBackToHomeScreenFromSimpleSearchScreen();
    }
}
