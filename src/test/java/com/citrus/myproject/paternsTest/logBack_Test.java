package com.citrus.myproject.paternsTest;


import ch.qos.logback.classic.LoggerContext;
import com.citrus.myproject.Master;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;


public class logBack_Test {
    private static final Logger logger = LoggerFactory.getLogger(logBack_Test.class);

    @Test
    public void hello() {
        logger.info("This is sample info message");
        logger.warn("This is sample warn message");
        logger.error("This is sample error message");
        logger.debug("This is sample debug message");

    }
}