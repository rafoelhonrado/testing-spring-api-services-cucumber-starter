package com.vichamalab.api.producto;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/vichamalab/api/producto")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, 
value = "pretty,"
		+ "html:target/reports/report.html, "
		+ "json:target/reports/report.json, "
		+ "junit:target/reports/report.xml,"
		+ "timeline:target/reports/timeline")
public class ProductSuiteTest {

}
