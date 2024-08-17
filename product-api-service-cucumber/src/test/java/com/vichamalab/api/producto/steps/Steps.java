package com.vichamalab.api.producto.steps;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import com.vichamalab.api.producto.dto.Product;
import com.vichamalab.api.producto.dto.ProductRequest;
import com.vichamalab.api.producto.dto.ProductResponse;
import com.vichamalab.api.producto.hooks.GlobalHook;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dada;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Steps {
	Response response;	
	public static String originalSku="ORIGINAL";
	String currentMethod ="";
	String nombreEsperado="";
	String descripcionEsperada="";
	float precioEsperado=0;
	String currentUrl="";
	
	@Dada("una API válida con url {string} y formato {string}")
	public void una_api_válida_con_url_y_formato(String url, String contentType) {
		RestAssured.requestSpecification = new RequestSpecBuilder()
		        .setContentType(contentType)
		        .setAccept(ContentType.JSON)
		        .build();
		currentUrl= url;
	}
	
	@Cuando("a la url {string}")
	public void a_la_url(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Cuando("a la API")
	public void a_la_api() {
		if ("POST".equals(currentMethod.toUpperCase())) {
			response = 	given()
							.spec(GlobalHook.requestSpec)
							.body(GlobalHook.productRequest)
						.when()
							.post(currentUrl)// Uri
						.then()
							.extract()
							.response();
		} else if ("PUT".equals(currentMethod.toUpperCase())) {
			nombreEsperado = GlobalHook.productRequest.getName();
			descripcionEsperada = GlobalHook.productRequest.getDescription();
			precioEsperado = GlobalHook.productRequest.getPrice();
			response = 	given()
					.log().all()
							.contentType(ContentType.JSON)// Headers
							.body(GlobalHook.productRequest)
						.when()
							.put(currentUrl + originalSku + "/")// Uri
						.then()
							.log().all()
							.extract().response();
		}
	}
	
	@Cuando("se hace una petición con el nombre {string}")
	public void se_hace_una_petición_con_el_nombre(String nombre) {
		GlobalHook.productRequest.setName(nombre);
	}
	
	@Cuando("se hace una petición {string} con el nombre {string}")
	public void se_hace_una_petición_con_el_nombre(String metodo, String nombre) {
		GlobalHook.productRequest.setName(nombre);
		currentMethod=metodo;
	}
	
	@Dada("y un producto valido previamente creado")
	public void y_un_producto_valido_previamente_creado() {
		GlobalHook.productRequest = new ProductRequest();
		GlobalHook.productRequest.setName("Nombre Original");
		GlobalHook.productRequest.setDescription("Descripcion Original");
		GlobalHook.productRequest.setPrice(100);
	   	 response = given()
	   			 	.spec(GlobalHook.requestSpec)
	   			 	.body(GlobalHook.productRequest)
	   			 .when()
	   			 	.post(currentUrl)
	   			 .then()
	   			 	.extract()
	   			 	.response();	   	 
	   	 originalSku = response.jsonPath().getString("sku");
	   	 System.out.println("originalSku:"+originalSku);
	}
	
	@Cuando("y la descripción {string}")
	public void y_la_descripción(String description) {
		GlobalHook.productRequest.setDescription(description);
	}
	
	@Cuando("y el precio {float}")
	public void y_el_precio(float price) {
		GlobalHook.productRequest.setPrice(price);
	}

	
	@Entonces("se recibe una respuesta exitosa con código {int}")
	public void se_recibe_una_respuesta_exitosa_con_código(Integer statusCode) {
		Assertions.assertEquals(statusCode,response.getStatusCode());
	}
	
	@Entonces("se recibe una respuesta {string} con código {int}")
	public void se_recibe_una_respuesta_de_error_con_código(String responseType,Integer statusCode) {
		Assertions.assertEquals(statusCode,response.getStatusCode());
	}
	
	@Entonces("y el mensaje {string}")
	public void y_el_mensaje(String message) {
		Assertions.assertEquals(message,response.jsonPath().getString("message"));
	}
	
	@Entonces("con los campos actualizados correctamente")
	public void con_los_campos_actualizados_correctamente() {
	   	 ProductResponse response = given()
	   		.contentType(ContentType.JSON)//Headers
	   	.when()
	   		.get(currentUrl+originalSku+"/")//Uri
	   	.then()
	   		.statusCode(HttpStatus.SC_OK)
	   		.extract()
	   		.as(ProductResponse.class);
	   	 Product product = response.getProducts().get(0);
	   	 Assertions.assertEquals(nombreEsperado, product.getName());
	   	 Assertions.assertEquals(descripcionEsperada, product.getDescription());
	   	 Assertions.assertEquals(precioEsperado, product.getPrice());
	}
	
	
	@Dada("un producto con nombre {string}")
	public void un_producto_con_nombre(String nombre) {
		GlobalHook.productRequest.setName(nombre);
	}
	@Dada("previamente creado con exito usando la ruta {string} y metodo {string}")
	public void previamente_creado_con_exito_usando_la_ruta_y_metodo(String ruta, String metodo) {
		currentUrl = "/api/v1/product/";
		if (!GlobalHook.inicializado) {
	   	 response = given()
	   			 	.spec(GlobalHook.requestSpec)
	   			 	.body(GlobalHook.productRequest)
	   			 .when()
	   			 	.post(ruta)
	   			 .then()
	   			 	.extract()
	   			 	.response();	   	 
	   	 originalSku = response.jsonPath().getString("sku");
	   	System.out.println("originalSku:"+originalSku);
	   	GlobalHook.inicializado=true;
		}
	}
	
	@Dada("un producto valido previamente creado")
	public void un_producto_valido_previamente_creado() {
		GlobalHook.productRequest.setName("Nombre Original");
		GlobalHook.productRequest.setDescription("Descripcion Original");
		GlobalHook.productRequest.setPrice(100);
		String ruta = "/api/v1/product/";
		currentUrl = "/api/v1/product/";
	   	 response = given()
	   			 	.spec(GlobalHook.requestSpec)
	   			 	.body(GlobalHook.productRequest)
	   			 .when()
	   			 	.post(ruta)
	   			 .then()
	   			 	.extract()
	   			 	.response();	   	 
	   	 originalSku = response.jsonPath().getString("sku");
	   	System.out.println("originalSku:"+originalSku);
	}
	

}
