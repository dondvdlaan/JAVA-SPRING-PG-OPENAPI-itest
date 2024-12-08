package dev.manyroads;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

//@RunWith(Cucumber.class)
//@CucumberOptions(
//        plugin = "html:target/reports.html",
//        features = "src/test/resources/features",
//        glue = "stepdefinitions",
//        dryRun = false,
//        tags = ""
//)
@Suite
@IncludeEngines("cucumber")
@SelectPackages("dev.manyroads")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = " dev.manyroads")
public class RunCucumberTest {
}
