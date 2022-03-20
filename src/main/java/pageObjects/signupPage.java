package pageObjects;
import utils.commandBase;

public class signupPage {
    commandBase cmd = new commandBase();

    //Elements in the signup screen
    String link_signupNow = "xpath==//android.view.View[@text='Sign up now']";
    String ft_firstName = "xpath==//android.view.View[@text='First name']/following-sibling::android.view.View/android.widget.EditText";
    String ft_lastName = "xpath==//android.view.View[@text='Last name']/following-sibling::android.view.View/android.widget.EditText";
    String ft_emailAddress = "xpath==//android.view.View[@text='Email address']/following-sibling::android.view.View/android.widget.EditText";
    String ft_password = "xpath==//android.view.View[@text='Password']/following-sibling::android.view.View/android.widget.EditText";
    String ft_confirmPassword = "xpath==//android.view.View[@text='Confirm password']/following-sibling::android.view.View/android.widget.EditText";
    String chk_termsAndConditions = "xpath==//android.widget.CheckBox[contains(@text,'Terms & Conditions and Privacy Policy')]";
    String btn_createAccount = "xpath==//android.widget.Button[@text='Create account']";

    public void clickOnSignupNowLink() {
        cmd.click(link_signupNow);
        cmd.staticWait(1000);
    }
    public void enterFirstName(String firstName) {
        cmd.type(ft_firstName,firstName);
    }
    public void enterLastName(String lastName) {
        cmd.type(ft_lastName,lastName);
    }
    public void enterEmail(String email) {
        cmd.type(ft_emailAddress,email);
    }
    public void enterPassword(String password) {
        cmd.type(ft_password,password);
    }
    public void enterConfirmPassword(String password) {
        cmd.type(ft_confirmPassword,password);
    }
    public void selectTermsAndConditionsCheckbox(){
        cmd.click(chk_termsAndConditions);
    }
    public void clickOnCreateAccountButton(){
        cmd.click(btn_createAccount);
    }

}
