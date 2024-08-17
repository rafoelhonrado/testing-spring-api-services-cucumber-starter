package com.vichamalab.api.producto.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;

public class StepHook {
	private static final Logger logger = LoggerFactory.getLogger(StepHook.class);
	
	@BeforeStep
	public void BeforeStep() {
		logger.info("StepHook -@BeforeStep");
	}
	
	@AfterStep
	public void AfterStep() {
		logger.info("StepHook -@AfterStep");
	}
}
