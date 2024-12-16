package dev.manyroads;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectPackages("dev.manyroads")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = " dev.manyroads")
public class RunCucumberTest {
}
