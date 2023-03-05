package com.citrus.myproject;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Optional;
import requests.ActionsHttpBases;
import requests.*;

import java.lang.reflect.Method;

@ContextConfiguration(classes = {requests.EndpointConfig.class} )


public class Master extends TestNGCitrusTestRunner {


    @Autowired
    protected HttpClient API;

    ActionsHttpBases http = new ActionsHttpBases(this);



    TestNGCitrusTestRunner TestRunner;
}
