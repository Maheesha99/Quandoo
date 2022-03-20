# Quandoo
Assignment for the Mobile QA engineer position

## How to setup the Windows Machine in order to execute the Test cases in ANDROID real device

My Specs:
- Device : Samsung A6+
- Screen Size : 6 inches
- Android Version : 10

### Setup the machine
- install java and set bin path in environment variable's "path" variable. Verify whether the path is set correctly by running
  ```java -version``` in the cmd.
  You will get the installed java version
- create new user variable called JAVA_HOME and give the path to JDK (EX: C:\Program Files\Java\jdk-17)
- Download and install node.js latest version. try ```node -v``` in the cmd and verify the version
- Download and install Appium Desktop. (https://github.com/appium/appium-desktop/releases/tag/v1.22.0). Get 'Appium-Server-GUI-windows-1.22.0.exe' from the link
- Download and install android studio.
- Create user variable called ANDROID_HOME and give the SDK path (EX : C:\Users\{username}\AppData\Local\Android\Sdk)
- set platform-tools and tools paths in 'path' variable (EX : C:\Users\{username}\AppData\Local\Android\Sdk\platform-tools, C:\Users\{username}\AppData\Local\Android\Sdk\tools)
- Try this ```adb devices``` on cmd to verify everything is ok. if not you get 'adb is not recognized as an internal or external command'
- Enable USB Debugging in your phone and connect to the machine. Authorize the device. Then try ```adb devices```. you will get all the devices attached to the machine
- Download and install Maven
- Get IntelliJ IDEA as the IDE

### Troubleshooting
- If your machine doesn't recognize the connected device, you have to install drivers(specific to the device) to the machine
- When you connect the device and try ```adb devices```, if you get device name along with 'unknown' text just disconnect the device,disable and enable USD debugging and reconnect to the machine.
- Once you reconnect, your device gets an alert asking whether to authorize the machine. Allow it. And then try ```adb devices```. you will get *List of devices attached
  d5ccda7f        device* as the result (d5ccda7f is my device name. You should get your device name there)

### Execute the Test cases
- start the appium server
- connect your device and have the device name in your hand (EX : d5ccda7f. run ```adb devices``` and get your device name)
- open the project in the IDE (I'm using IntelliJ IDEA)
- open src/main/java/resources/conf.properties file
- change the device name and platform version accordingly and save the file (platform version can be found in the device settings)
- Then right click on the src/test/java/TestRunner
- Then click on 'RunTestRunner'
- device should be unlocked
- Enable Location in the device

### Reporting
- Once the execution is done .html,.json and .xml file will be getting generated in the target folder
- open the .html file in the browser and you can see the cucumber report

### Execute specific tags only
- open the feature file and add whatever the tag for any of the scenarios
- Then open the TestRunner class and specify the tag you want to execute and then run the test runner

### logs
- log file will be generated in the logs folder

## Tool Selection
- Appium
    - Community support is high
    - stable
    - opensource
    - supports for many programming languages
    - It supports both Android and iOS
- Cucumber
    - it allows the involvement of non-programmers as well. BA/Manager also can review and understand the scripts
    - Report can be easily generated and readable and sharable.
    - Reusable code
    - Maintenance is easy
- Page Object Model
    - Re-Usability
    - Maintenance is easy
- Log4j2
    - good community support, custom log levels
    - Clear logs with date and time
    - logs can be saved to the external files and sharable
- maven
    - Easy build tool
    - Simplify the build processes

## Automation Framework

### wrapping commands

I have implemented wrapping commands in src/main/java/helpers/CommandBase in order to help non-tech persons to use the framework easily. Below are the implemented commands and they can be used in any class by creating an object of the CommandBase class

usage:
```
String btn_login="xpath==//input[@id='id']";

CommandBase cmd = new CommandBase();
cmd.click(btn_login);
```

| Command                   | Return Type          |                        
| ------                    | ------               |                
|launchTheApp()            |void                 |
|closeTheApp()              |void                  |
|click(String element)             |void                  |
|click(String element,String parameter)   |void                  |
|type(String element,String value)        |void                  |
|type(String element, String value, String param)|void|
|verifyElementIsPresent(String element)|void|
|verifyElementIsPresent(String element, String param)|void|
|verifyElementIsPresent_softVerification(String element)|boolean|
|verifyElementIsPresent_softVerification(String element, String param)|boolean|
|verifyElementIsPresent_softVerification(String element, String param, int timeOutInSeconds)|boolean|
|scrollDownTheScreen(String textToVisible)|void|
|scrollHorizontally(String resourceID, String category)|void|
|staticWait(long milliSec)|void|
|getElementTextAttribute(String element)|String|
|getElementTextAttribute(String element,String param)|String|
|verifyElementTextAttribute(String element, String expectedValue)|void|
|verifyElementTextAttribute(String element,String param, String expectedValue)|void|
|switchToWebContent()|void|
|switchToNativeContent()|void|
|getCurrentDate()|LocalDate|
|verifyPassOrFail(Boolean status)|void|
|returnWebElement(String element)|WebElement|
|returnWebElement(String element, String param)|WebElement|
|returnWebElement(String element, String param, int timeOut)|WebElement|
|resolveElement(By by)|WebElement|
|resolveElement(By by, int timeoutInSeconds)|WebElement|




### Define elements
In this framework we define elements in the src/main/java/pageObjects folder

element needs to be defined as a String
- that will help to handle parameterized elements
- log the execution steps along with the element attributes

**Usage of non parameterized elements**
```
1. String btn_allowOnlyWhileUsingTheApp = "xpath==//android.widget.Button[contains(@resource-id,'permission_allow_foreground_only_button')]";
2. String tf_searchHomeScreen = "id==com.pickery.app:id/button_open_search";
```
1. 1st object I have given the xpath. you have to use ```xpath==``` prefix and then the xpath of the element
2. 2nd object I have given the id. you have to use ```id==``` prefix and then the id/resourceId of the element
3. If you want to use class attribute, then use ```class==``` prefix and class value

How to pass the element to wrapping commands
```
cmd.click(btn_allowOnlyWhileUsingTheApp);
```
**Usage of parameterized elements**
```
1. String card_product = "xpath==//android.widget.TextView[@text='<>']/../..";
```
1. If you want to click on the given product or category card, then you have to have a one generic element and pass the name of the product. In that case what we have to do is add
   ```'<>'``` symbol where you want to parameterize.

How to pass the element to wrapping commands
```
String restaurantName="MacDonalds";
cmd.click(card_product,restaurantName);
```
## Naming Conventions

|           Class or element     |   How to Name it       |  Example|                     
| ------                    | ------               |  ----|    
|pageObjects|name of the screen + 'Page'|signupPage|
|stepDefinitions|name of the screen + 'Steps'|signupSteps|
|elements|prefix + camel case|see below table 1.2 for the element prefixes|
stepDefinition Methods|camel case|userGoBackFromTheAdvanceSearchView()|

**Table 1.2 - element prefixes**

|           element     |   Prefix     |  Usage|                     
| ------                    | ------               |  ----|    
|filter_mainFilter|filter|filter option (open now)|
|rdo_selections|rdo|radio button|
|btn_apply|btn|button|
|view_peopleCount|view|Scrollable view|
|lbl_discovery|lbl|label|
|hmb_loginOrSignup|hmb|Hamburger menu items|
|tf_search| tf| text field|
|category_resultsOfRestaurants|category| card or div|
|icon_back|icon|icon|
|tab_review|tab|tab|
|link_signupNow|link|link|
|chk_termsAndConditions|chk|checkbox|

## Test Cases
Attached an Excel file into the ***z_supportDocs*** folder

SNAPSHOT version details
-----------------------------

| Date              | Version            | Comment                                                                                                   |                               
| ------            | ------             | -------                                                                                                   |                               
| 20th March 2022      | 1.0-SNAPSHOT       |Initial Version - with POM file dependency version updates and basic framework                             |

