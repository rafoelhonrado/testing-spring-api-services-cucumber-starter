package com.vichamalab.api.producto.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ScenarioHook {
	private static final Logger logger = LoggerFactory.getLogger(ScenarioHook.class);
	
	@Before
	public void SetupScenario() {
		logger.info("ScenarioHook -@Before");
	}
	
	@After
	public void CleanScenarios() {
		logger.info("ScenarioHook -@After");
	}

}
