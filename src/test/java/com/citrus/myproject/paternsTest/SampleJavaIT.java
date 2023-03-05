package com.citrus.myproject.paternsTest;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.context.TestContext;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;


import static com.consol.citrus.actions.EchoAction.Builder.echo;

/**
 * This is a sample Java DSL Citrus integration test.
 *
 * @author Citrus
 */

@Test
public class SampleJavaIT extends TestNGCitrusSpringSupport {


    @CitrusTest
    @Test
    @Parameters("context")
    public void echoToday(@Optional @CitrusResource TestContext context) {
        variable("now", "citrus:currentDate()");

        System.out.println("Today is : " + context.getVariable("now"));

    }


    @CitrusTest(name = "SampleJavaTest.sayHello")
    public void sayHello() {
        run(echo("Hello Citrus!"));
    }
}
