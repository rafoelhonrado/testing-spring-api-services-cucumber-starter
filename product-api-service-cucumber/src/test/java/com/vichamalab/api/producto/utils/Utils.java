package com.vichamalab.api.producto.utils;

import com.vichamalab.api.producto.dto.ProductRequest;

public class Utils {
	public static final String USER_AGENT="JBEnterprise";
	
    public static ProductRequest generateNewProductRequest() {
    	return ProductRequest.builder()
    			.name("Iphone 15")
    			.description("Un equipo moderno de la marca Apple")
    			.price(1400)
    			.build();    	
    }
    
    public static ProductRequest generateNewProductRequest(String name,String description, float price) {
    	return ProductRequest.builder()
    			.name(name)
    			.description(description)
    			.price(price)
    			.build();    	
    }  
}
