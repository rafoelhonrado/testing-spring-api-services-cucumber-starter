package com.vichamalab.api.producto.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vichamalab.api.producto.dto.ProductRequest;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class GlobalHook {
	public static ProductRequest productRequest;
	public static RequestSpecification requestSpec;
	public static boolean inicializado;
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalHook.class);
	
	@BeforeAll
	public static void SetupScenarios() {
		logger.info("GlobalHook -@BeforeAll");
		RestAssured.baseURI="http://localhost:8081";
		productRequest = new ProductRequest();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("User-Agent", com.vichamalab.api.producto.utils.Utils.USER_AGENT);
        builder.addHeader("Authorization","Bearer aGFzaGRzZnNkZnNkZnNkZnNk");
        builder.setContentType(ContentType.JSON);
        requestSpec = builder.build();
	}
	
	@AfterAll
	public static void CleanScenarios() {
		logger.info("GlobalHook -@AfterAll");
		//Limpiar BD, eliminar referencias
	}
}
