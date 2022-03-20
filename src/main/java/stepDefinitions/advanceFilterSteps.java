package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.advanceFilterPage;
import pageObjects.restaurantPage;

public class advanceFilterSteps {
    //This class maintains all the step definitions related to advanced filter flow

    advanceFilterPage pg_advanceFilter = new advanceFilterPage();
    restaurantPage pg_restaurant = new restaurantPage();

    @Then("user do an advance filter by having {string},{string},{string}")
    public void userDoAnAdvanceFilterByHaving(String numOfPeople, String date, String time) {
        //filter by number of people,date and time
        pg_advanceFilter.userClickOnAdvancedFilter();
        pg_advanceFilter.selectNumberOfPeople(numOfPeople);
        pg_advanceFilter.selectADate(date);
      //  pg_advanceFilter.selectATime(time);
        pg_advanceFilter.clickOnApplyButton();
    }

    @Then("user go back from the advance search view")
    public void userGoBackFromTheAdvanceSearchView() {
        pg_restaurant.goBackToHome();
    }
}
