package pageObjects;

import utils.commandBase;

import java.time.LocalDate;

public class advanceFilterPage {
    commandBase cmd = new commandBase(); //create an object from the commandBase in order to use the implemented wrapping commands

    //Elements in the advance filter screen
    String filter_mainFilter = "id==de.quandoo.android.consumerapp:id/search_results_availability_details_area";
    String rdo_selections = "xpath==//android.widget.RadioButton[contains(@text,'<>')]"; //parameterized element (generalized)
    String btn_apply = "id==de.quandoo.android.consumerapp:id/apply_button";
    //String view_peopleCount = "id==de.quandoo.android.consumerapp:id/people_count_recycler_view";
    String view_time = "id==de.quandoo.android.consumerapp:id/time_recycler_view";

    public void userClickOnAdvancedFilter() {
        cmd.click(filter_mainFilter);
    }

    public void selectNumberOfPeople(String numberOfPeople) {
        cmd.click(rdo_selections, numberOfPeople);
    }

    public void selectADate(String date) {
        LocalDate today = cmd.getCurrentDate(); // get system date
        String dateNew = null;
        switch (date) {
            case "today":
                dateNew = String.valueOf(today.getDayOfMonth());
                break;
            case "nextday":
                LocalDate nextDay = today.plusDays(1);
                dateNew = String.valueOf(nextDay.getDayOfMonth());
        }
        cmd.click(rdo_selections, dateNew);
    }

    public void selectATime(String time) {
        cmd.scrollHorizontally(view_time, time);
        cmd.click(rdo_selections, time);
    }

    public void clickOnApplyButton() {
        cmd.click(btn_apply);
    }
}
