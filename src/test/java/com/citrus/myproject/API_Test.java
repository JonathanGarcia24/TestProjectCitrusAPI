package com.citrus.myproject;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.runner.TestRunner;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static Param.ParamEnvironnement.client;
import static com.consol.citrus.actions.EchoAction.Builder.echo;

public class API_Test extends Master{


    @CitrusTest
    @Test
    @Parameters("context")
    public void Getvalue_Test(@Optional @CitrusResource TestContext context){

        JSONObject Hello = http.get(API, "/read", "?description=caca","Get du 1er object", context);
        System.out.println(Hello);

    }

}
