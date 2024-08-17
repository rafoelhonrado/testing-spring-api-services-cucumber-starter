package com.vichamalab.api.producto.steps_basic;

import com.vichamalab.api.producto.dto.ProductRequest;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dada;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;

public class StepsBasic {
	private Response response;
	private ProductRequest productRequest;

	@Dada("una API válida con formato {string}")
	public void una_api_válida_con_formato(String formato) {
		productRequest = new ProductRequest();
		RestAssured.requestSpecification = new RequestSpecBuilder()
		        .setContentType(formato)
		        .setBaseUri("http://localhost:8081")
		        .setAccept(ContentType.JSON)
		        .addHeader("User-Agent", com.vichamalab.api.producto.utils.Utils.USER_AGENT)
		        .addHeader("Authorization","Bearer aGFzaGRzZnNkZnNkZnNkZnNk")
		        .build();
	}
	
	@Cuando("se hace una petición con el nombre {string}")
	public void se_hace_una_petición_con_el_nombre(String name) {
		productRequest.setName(name);
	}
	
	@Cuando("y la descripción {string}")
	public void y_la_descripción(String description) {
		productRequest.setDescription(description);
	}
	
	@Cuando("y el precio {float}")
	public void y_el_precio(float price) {
		productRequest.setPrice(price);
	}
	
	@Cuando("a la url {string}")
	public void a_la_url(String path) {
		response = 	given()
				.body(productRequest)
			.when()
				.post(path)// Uri
			.then()
				.extract()
				.response();
	}
	@Entonces("se recibe una respuesta exitosa con código {int}")
	public void se_recibe_una_respuesta_exitosa_con_código(Integer statusCode) {
		Assertions.assertEquals(statusCode,response.getStatusCode());
	}
	
	
	@Entonces("y el mensaje {string}")
	public void y_el_mensaje(String message) {
		Assertions.assertEquals(message,response.jsonPath().getString("message"));
	}
}
