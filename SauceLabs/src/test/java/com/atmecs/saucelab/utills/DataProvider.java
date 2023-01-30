package com.atmecs.saucelab.utills;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.lf5.util.ResourceUtils;
import org.json.simple.parser.ParseException;

import com.atmecs.saucelab.pageObject.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProvider {
	
	public static <T> T createObjectFromJsonFile(String filePath, Class<T> clazz) {
	    try {
	    	ObjectMapper mapper = new ObjectMapper();
	    	return mapper.readValue(new File(filePath), clazz);
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }
	
	public static void main(String args[]) {
		CartItem[] v = createObjectFromJsonFile("src/test/resources/cartTestData2.json", CartItem[].class);
		System.out.println("the json file from object is "+ 
		v);
		for(CartItem cartItems : v ) {
			System.out.println("the cart items are "+cartItems);
		}
		  
	}

}
