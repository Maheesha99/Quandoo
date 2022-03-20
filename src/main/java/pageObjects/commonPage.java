package pageObjects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.commandBase;

public class commonPage {
    commandBase cmd = new commandBase(); //create an object from the commandBase in order to use the implemented wrapping commands
    Logger log = LogManager.getLogger();

    //All the common elements have been assigned to the string variables
    String lbl_discovery = "id==de.quandoo.android.consumerapp:id/toolbar_header_title_text_view";
    String btn_allowOnlyWhileUsingTheApp = "xpath==//android.widget.Button[contains(@resource-id,'permission_allow_foreground_only_button')]";
    String bmb_hamburgerMainMenu = "xpath==//android.widget.ImageButton[@content-desc='Login']";
    String hmb_loginOrSignup = "xpath==//android.widget.TextView[@text='Log in or sign up']";
    String hmb_logOut = "xpath==//android.widget.TextView[@text='Log Out']";
    String tf_search = "xpath==//android.widget.TextView[@text='Search for a restaurant or cuisine']";
    String tf_cuisineOrRestaurantName = "id==de.quandoo.android.consumerapp:id/search_query";
    String tf_location = "id==de.quandoo.android.consumerapp:id/search_destination";
    String lbl_result = "xpath==//android.widget.TextView[@text='<>']/..";
    String lbl_filterOption = "xpath==//android.widget.TextView[@text='<>']";
    String category_resultsOfRestaurants = "xpath==(//androidx.recyclerview.widget.RecyclerView)[2]/android.widget.FrameLayout[<>]";
    String lbl_restaurantName = "xpath==(//androidx.recyclerview.widget.RecyclerView)[2]/android.widget.FrameLayout[<>]//android.widget.TextView";
    String btn_loginButton = "xpath==//android.widget.Button[@text='Log in']";
    String icon_back = "xpath==//android.widget.TextView[@text='Search']/../android.widget.ImageButton";
    String lbl_restaurantNameInDetailScreen = "id==de.quandoo.android.consumerapp:id/merchant_info_title_text_view";
    String lbl_filterText = "xpath==//android.widget.TextView[@text='<>']";

    public void clickOnAllowOnlyWhileUsingTheAppButton() {
        //see whether the btn_allowOnlyWhileUsingTheApp element is available. If so click on it. Else skip
        try {
            boolean elementIsPresent = false;
            elementIsPresent = cmd.verifyElementIsPresent_softVerification(btn_allowOnlyWhileUsingTheApp);
            if (elementIsPresent) {
                cmd.click(btn_allowOnlyWhileUsingTheApp);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void verifyTheLabelInHomePage(String label) {
        //verify user navigated to the correct page
        try {
            cmd.verifyElementTextAttribute(lbl_discovery, label);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void clickOnHamburgerMenu() {
        //user click on hamburger menu
        try {
            cmd.click(bmb_hamburgerMainMenu);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void clickOnLoginOrSignup() {
        //user click on login or signup option from the hamburger menu
        try {
            cmd.click(hmb_loginOrSignup);
            cmd.staticWait(2000);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void clickOnLogout() {
        //user click on logout from the hamburger menu
        cmd.click(hmb_logOut);
        cmd.staticWait(2000);

    }

    public void goBackToHomeScreenFromSimpleSearchScreen() {
        cmd.click(icon_back);
    }

    public void searchForARestaurant(String cuisine, String location) {
        //search a restaurant by location and type
        try {
            cmd.click(tf_search);
            //cmd.type(tf_location, location);
            cmd.click(tf_location);
            cmd.click(lbl_result, location);
            //cmd.type(tf_cuisineOrRestaurantName, cuisine);
            cmd.click(tf_cuisineOrRestaurantName);
            cmd.click(lbl_result, cuisine);
            cmd.verifyElementIsPresent(lbl_filterText, cuisine + " in " + location); //verify whether the correct filter has been applied
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void userSelectAFilter(String filter) {

        cmd.staticWait(3000);
        cmd.click(lbl_filterOption, filter);
    }

    public void userSelectARestaurantFromTheResults(String itemNumber) {
        cmd.staticWait(3000);
        String restaurantName = cmd.getElementTextAttribute(lbl_restaurantName, itemNumber);//store restaurant name
        cmd.click(category_resultsOfRestaurants, itemNumber);
        cmd.verifyElementTextAttribute(lbl_restaurantNameInDetailScreen, restaurantName); //Verify user has navigated to correct restaurant
    }

    public void scrollDownThePage(String text) {
        cmd.scrollDownTheScreen(text);
    }

    public void verifyLoginButtonIsAvailable() {
        cmd.verifyElementIsPresent(btn_loginButton);
    }

    public void switchToNativeContent() {
        cmd.switchToNativeContent();
    }

    public void switchToWebContent() {
        cmd.switchToWebContent();
    }
}
