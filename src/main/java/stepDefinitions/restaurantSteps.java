package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.restaurantPage;

public class restaurantSteps {

    //This class maintains all the step definitions related to restaurant page
    restaurantPage pg_restaurant = new restaurantPage();

    @Then("user navigate to review section of the restaurant")
    public void userScrollToReviewSectionOfTheRestaurant() {
        pg_restaurant.userNavigateToTheReviewSection();
    }

    @Then("user go back from the restaurant details view")
    public void userGoBackFromTheRestaurantDetailsView() {
        pg_restaurant.goBackToHome();
    }
}
