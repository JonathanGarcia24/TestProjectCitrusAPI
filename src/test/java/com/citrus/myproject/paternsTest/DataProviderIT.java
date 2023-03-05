package com.citrus.myproject.paternsTest;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.actions.SleepAction.Builder.sleep;

public class DataProviderIT extends TestNGCitrusSupport{

    @CitrusTest
    @CitrusParameters( {"message", "delay"} )
    @Test(dataProvider = "messageDataProvider")
    public void dataProvider(String message, Long sleep){
        echo("message");
        echo().message("message");
        run(sleep().milliseconds(sleep));

        run(echo("${message}"));
        run(echo("${delay}"));

    }

    @DataProvider
    public Object[][] messageDataProvider() {
        return new Object[][] {
                { "Hello World!", 300L },
                { "Citrus rocks!", 1000L },
                { "Hi from Citrus!", 500L },
        };
    }


}