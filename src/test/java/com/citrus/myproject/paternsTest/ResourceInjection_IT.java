package com.citrus.myproject.paternsTest;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.actions.SleepAction;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.actions.CreateVariablesAction.Builder.createVariable;
import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.actions.SleepAction.Builder.sleep;

public class ResourceInjection_IT extends TestNGCitrusSupport {
    private static final Logger logger = LoggerFactory.getLogger("com.consol.citrus.LoggingReporter");


    @Test
    @CitrusTest
    public void injectResources(@Optional @CitrusResource TestCaseRunner runner,
                                @Optional @CitrusResource TestContext context) {

        runner.given(
                createVariable("random", "citrus:randomNumber(10)")
        );

        runner.run(echo("The random number is: ${random}"));

        logger.info("1st variable OK");

        try {
            Thread.sleep(10000); // pause de 20 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.run(
                echo("The second random number is: ${random}")
        );

        logger.info("2nd variable OK");

    }

    @Test
    @CitrusTest
    public void injectResources2(@Optional @CitrusResource TestCaseRunner runner,
                                @Optional @CitrusResource TestContext context) {

        variable("random", "citrus:randomNumber(10)");

        runner.run(echo("Random number : " + context.getVariable("random")));

    }
}
