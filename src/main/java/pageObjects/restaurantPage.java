package pageObjects;

import utils.commandBase;

public class restaurantPage {
    commandBase cmd = new commandBase();

    //Elements in the restaurant details screen
    String tab_review = "xpath==//androidx.appcompat.app.a.c[@content-desc='Reviews']";
    String lbl_reviewSummary = "id==de.quandoo.android.consumerapp:id/review_summary_background_view";
    String icon_back = "xpath==//android.widget.ImageButton[@content-desc='Navigate up']";

    public void userNavigateToTheReviewSection() {
        cmd.click(tab_review);
        cmd.verifyElementIsPresent(lbl_reviewSummary);
    }

    public void goBackToHome() {
        cmd.staticWait(1000);
        cmd.click(icon_back);
    }
}
