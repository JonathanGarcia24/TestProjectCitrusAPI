package com.citrus.myproject.paternsTest;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.spring.JUnit4CitrusSpringSupport;
import org.testng.annotations.Test;

import static com.consol.citrus.actions.EchoAction.Builder.echo;

@Test
public class SampleJavaJunit4IT extends JUnit4CitrusSpringSupport {


    @CitrusTest
    public void sayHelloFromJUnit(){
        echo("Hello from junit");
    }
}
