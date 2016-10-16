package com.example.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by upgundecha on 13/10/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-html-report",
        "json:target/cucumber.json"}, features = {"."})
public class RunCukesTest {
    @BeforeClass
    public static void setUpSuite() {
        System.setProperty("jagacy.properties.dir",
                "./src/test/resources");
        System.setProperty("test.window", "true");
    }
}
