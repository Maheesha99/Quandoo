package utils;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

public class hooks {
    private int currentStepIndex = 0;
    static Logger log = LogManager.getLogger();
    commandBase cmd = new commandBase();

    @Before
    public void setup(Scenario scenario) throws MalformedURLException {
        //executes before each and every scenario. Launch the app
        try {
            cmd.launchTheApp();
            System.out.print("\n");
            log.info(scenario.getName());
            System.out.println();
            log.info("***** TEST STARTED *****");
        } catch (Exception e) {
            log.error(e);
        }
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        //Print test steps
        log.info("TEST STEP : " + getStepText(scenario));

    }

    @AfterStep
    public void afterStep() {
        currentStepIndex += 1;
    }

    @After
    public void teardown(Scenario scenario) {
        //executes after each and every scenario
        try {
            Status status = scenario.getStatus();
            if (status.equals(Status.FAILED)) {
                scenario.attach(((TakesScreenshot) cmd.driver).getScreenshotAs(OutputType.BYTES), "image/png", "FailedAttachment"); // attaching failure point screenshot to the report
                log.error("***** Ending Scenario : " + scenario.getName() + " *****");
                log.error("***** TEST FAILED *****");

            }
            if (status.equals(Status.SKIPPED)) {
                log.warn("***** Ending Scenario : " + scenario.getName() + " *****");
                log.warn("***** TEST SKIPPED *****");

            }
            if (status.equals(Status.PASSED)) {
                log.info("***** Ending Scenario : " + scenario.getName() + " *****");
                log.info("***** TEST PASSED *****");
            }
            cmd.closeTheApp(); // kill the app
        } catch (Exception e) {
            log.error(e);
        }
    }

    public String getStepText(Scenario scenario) {
        //Print the current Test Step
        String currentStepDescr = null;
        java.lang.reflect.Field f = null;
        TestCaseState tcs = null;
        Field f2 = null;
        TestCase r = null;
        try {
            f = scenario.getClass().getDeclaredField("delegate");
            f.setAccessible(true);

            tcs = (TestCaseState) f.get(scenario);

            f2 = tcs.getClass().getDeclaredField("testCase");
            f2.setAccessible(true);

            r = (TestCase) f2.get(tcs);
            List<PickleStepTestStep> stepDefs = r.getTestSteps()
                    .stream()
                    .filter(x -> x instanceof PickleStepTestStep)
                    .map(x -> (PickleStepTestStep) x)
                    .collect(Collectors.toList());

            PickleStepTestStep currentStepDef = stepDefs
                    .get(currentStepIndex);
            currentStepDescr = currentStepDef.getStep().getText();
        } catch (Exception e) {
            log.error(e);
        }
        return currentStepDescr;
    }
}
