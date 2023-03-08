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
    @Test(priority = 1)
    @Parameters("context")
    public void Postvalue_Test(@Optional @CitrusResource TestContext context){

        JSONObject Hello = http.post(API, "/create", "API/POST_API.json","produit", context);
        echo(String.valueOf(Hello));
    }


    @CitrusTest
    @Test(priority = 2)
    @Parameters("context")
    public void GetValue_Test(@Optional @CitrusResource TestContext context){

        JSONObject Hello = http.get(API, "/read", "1","Get du 1er object", context);

        //System.out.println(Hello);
        echo(String.valueOf(Hello));
    }


    @CitrusTest
    @Test(priority = 3)
    @Parameters("context")
    public void UpdateValue_Test(@Optional @CitrusResource TestContext context){

        JSONObject Hello = http.put(API, "/update", "1","API/PUT_API.json","produit", context);

        //System.out.println(Hello);
        echo(String.valueOf(Hello));
    }


    @CitrusTest
    @Test(priority = 4)
    @Parameters("context")
    public void DeleteValue_Test(@Optional @CitrusResource TestContext context){

        String Hello = http.delete(API, "/delete", "2","produit", context);

        System.out.println(Hello);
    }


}
