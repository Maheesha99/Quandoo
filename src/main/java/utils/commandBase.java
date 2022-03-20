package utils;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

public class commandBase {
    //All the selenium commands have been written as wrapping commands.

    public static AndroidDriver driver; //define the android driver
    static Logger log = LogManager.getLogger(commandBase.class); //log4j logger
    static readPropertyFile prop = new readPropertyFile(); //get property file values
    int TIMEOUTINSECONDS = 10; //Default timeout

    public void launchTheApp() throws MalformedURLException {
        //Runs in the before hook. set all the capabilities and initialize the driver
        try {
            String host = prop.getHost();
            String port = prop.getPort();
            String platformName = prop.getPlatformName();


            if (platformName.equalsIgnoreCase("android")) {
                String deviceName = prop.getDeviceName();
                String platformVersion = prop.getPlatformVersion();

                log.info("DEVICE NAME : " + deviceName + " , PLATFORM : " + platformName + " , PLATFORM VERSION : " + platformVersion + " , HUB URL : " + host + ":" + port + "/wd/hub/");

                DesiredCapabilities caps = new DesiredCapabilities();

                caps.setCapability("deviceName", deviceName);
                caps.setCapability("platformName", platformName);
                caps.setCapability("platformVersion", platformVersion);
                caps.setCapability("appPackage", "de.quandoo.android.consumerapp");
                caps.setCapability("appActivity", "com.quandoo.consumer.legacy.ui.general.SplashScreenActivity");
                caps.setCapability("noReset", "false");
                driver = new AndroidDriver(new URL(host + ":" + port + "/wd/hub/"), caps);


            } else if (platformName.equalsIgnoreCase("ios")) {
                //add ios specific desired capabilities and driver here
            }

        } catch (Exception e) {
            log.error(e);
        }
    }

    public void closeTheApp() {
        //Runs in the after hook
        try {
            driver.quit();
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void click(String element) {
        log.info("User Action : Click");
        Boolean status = false;
        try {
            returnWebElement(element).click();
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public void click(String element, String param) {
        //click method overloaded for parameterized elements
        log.info("User Action : Click");
        Boolean status = false;
        try {
            returnWebElement(element, param).click();
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public void type(String element, String value) {

        Boolean status = false;
        log.info("User Action : Type");
        log.info("Value : " + value);
        try {
            returnWebElement(element).sendKeys(value);
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public void type(String element, String value, String param) {

        Boolean status = false;
        log.info("User Action : Type");
        log.info("Value : " + value);
        try {
            returnWebElement(element, param).sendKeys(value);
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public void verifyElementIsPresent(String element) {
        log.info("User Action : Verify Element Is Present");
        Boolean status = false;
        try {
            returnWebElement(element);
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public void verifyElementIsPresent(String element, String param) {
        log.info("User Action : Verify Element Is Present");
        Boolean status = false;
        try {
            returnWebElement(element, param).isDisplayed();
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public boolean verifyElementIsPresent_softVerification(String element) {
        //just return the boolean value.This method does not fail and stop the execution
        log.info("User Action : Verify Element Is Present");
        Boolean status = false;
        try {
            returnWebElement(element).isDisplayed();
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        return status;
    }

    public boolean verifyElementIsPresent_softVerification(String element, String param) {
        //just return the boolean value.This method does not fail and stop the execution
        log.info("User Action : Verify Element Is Present");
        Boolean status = false;
        try {
            returnWebElement(element, param).isDisplayed();
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        return status;
    }

    public boolean verifyElementIsPresent_softVerification(String element, String param, int timeOutInSeconds) {
        //just return the boolean value.This method does not fail and stop the execution
        log.info("User Action : Verify Element Is Present");
        Boolean status = false;
        try {
            returnWebElement(element, param, timeOutInSeconds).isDisplayed();
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        return status;
    }

    public void scrollDownTheScreen(String textToVisible) {
        boolean status = false;
        log.info("User Action : Scroll Down");
        try {
            WebElement ele = driver.findElement(MobileBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToVisible + "\"))"));
            status = true;

        } catch (Exception e) {
            log.error(e);
        }

        verifyPassOrFail(status);
    }

    public void scrollHorizontally(String resourceID, String category) {
        boolean status = false;
        log.info("User Action : Scroll Horizontally");
        try {
            WebElement ele = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"" + resourceID.split("==")[1] + "\").scrollable(true)).setAsHorizontalList().scrollIntoView(new UiSelector().text(\"" + category + "\"))"));
            status = true;
        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public void staticWait(long milliSec) {
        //Thread.sleep used at some places in order to focus on an element
        try {
            log.info("User Action : Wait Until " + milliSec + " milliseconds");
            Thread.sleep(milliSec);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public String getElementTextAttribute(String element) {
        boolean status = false;
        String actualValue = null;
        try {
            log.info("User Action : Get Element Text Attribute");

            actualValue = returnWebElement(element).getText();
            log.info("Actual Value : " + actualValue);

        } catch (Exception e) {
            log.error(e);
        }
        return actualValue;
    }
    public String getElementTextAttribute(String element,String param) {
        boolean status = false;
        String actualValue = null;
        try {
            log.info("User Action : Get Element Text Attribute");

            actualValue = returnWebElement(element,param).getText();
            log.info("Actual Value : " + actualValue);

        } catch (Exception e) {
            log.error(e);
        }
        return actualValue;
    }
    public void verifyElementTextAttribute(String element, String expectedValue) {
        boolean status = false;
        String actualValue = null;
        try {
            log.info("User Action : Verify Element Text Attribute");

            actualValue = returnWebElement(element).getText();
            log.info("Expected Value : " + expectedValue + " | Actual Value : " + actualValue);

            if (actualValue.contains(expectedValue)) {
                status = true;
            } else {
                log.error("Values mismatch");
            }

        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }
    public void verifyElementTextAttribute(String element,String param, String expectedValue) {
        boolean status = false;
        String actualValue = null;
        try {
            log.info("User Action : Verify Element Text Attribute");

            actualValue = returnWebElement(element,param).getText();
            log.info("Expected Value : " + expectedValue + " | Actual Value : " + actualValue);

            if (actualValue.contains(expectedValue)) {
                status = true;
            } else {
                log.error("Values mismatch");
            }

        } catch (Exception e) {
            log.error(e);
        }
        verifyPassOrFail(status);
    }

    public WebElement returnWebElement(String element) {
        //split the element string and return the webElement
        WebElement ele = null;
        try {
            String attribute = element.split("==")[0];
            String value = element.split("==")[1];

            log.info("Attribute : " + attribute + " | value : " + value);

            ele = resolveElement(getElementAttribute(attribute, value));

        } catch (Exception e) {
            log.error(e);
        }
        return ele;
    }

    public WebElement returnWebElement(String element, String param) {
        WebElement ele = null;
        try {
            String attribute = element.split("==")[0];
            String value = element.split("==")[1];
            value = value.replace("<>", param);

            log.info("Attribute : " + attribute + " | value : " + value);

            ele = resolveElement(getElementAttribute(attribute, value));

        } catch (Exception e) {
            log.error(e);
        }
        return ele;
    }

    public WebElement returnWebElement(String element, String param, int timeOut) {
        WebElement ele = null;
        try {
            String attribute = element.split("==")[0];
            String value = element.split("==")[1];
            value = value.replace("<>", param);

            log.info("Attribute : " + attribute + " | value : " + value);


            ele = resolveElement(getElementAttribute(attribute, value), timeOut);

        } catch (Exception e) {
            log.error(e);
        }
        return ele;
    }

    public WebElement resolveElement(By by) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUTINSECONDS))
                .pollingEvery(Duration.ofSeconds(2L))
                .ignoring(NoSuchElementException.class);
        WebElement ele = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return ele;
    }

    public WebElement resolveElement(By by, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(2L))
                .ignoring(NoSuchElementException.class);
        WebElement ele = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return ele;
    }

    public By getElementAttribute(String attribute, String value) {
        //Return the By value based on the element String attribute user has given
        By resolvedElement = null;
        try {
            switch (attribute.toLowerCase()) {
                case "id":
                    resolvedElement = By.id(value);
                    break;
                case "xpath":
                    resolvedElement = By.xpath(value);
                    break;
                case "class":
                    resolvedElement = By.className(value);
                    break;
                case "css":
                    resolvedElement = By.cssSelector(value);
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }
        return resolvedElement;
    }

    public void switchToWebContent() {
        try {
            String webContext;
            Set<String> contextNames = driver.getContextHandles();
            for (String contextName : contextNames) {
                System.out.println(contextName); //prints out all the contexts available
                if (contextName.contains("WEBVIEW")) {
                    driver.context(contextName); // set context to WEBVIEW context
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void switchToNativeContent() {
        try {
            driver.context("NATIVE_APP");
        } catch (Exception e) {
            log.error(e);
        }
    }

    public LocalDate getCurrentDate() {
        return java.time.LocalDate.now();
    }

    public void verifyPassOrFail(Boolean status) {
        //in order to fail or pass the test step, used assertTrue and passed the status of each and every user action
        Assert.assertTrue(status);
    }
}
