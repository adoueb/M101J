package com.tengen;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {
	public static void main(String[] args) {
		
		// Freemarker configuration.
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

		// Java 8 style (lambda expression).
		Spark.get("/", (request, response) -> {
			StringWriter writer = new StringWriter();
			try {
				Map<String, Object> fruitsMap = new HashMap<String, Object>();
				fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
				
				Template fruitPickerTemplate = configuration.getTemplate("FruitPicker.ftl");
				
				fruitPickerTemplate.process(fruitsMap, writer);				
				
			} catch (Exception e) {
				//halt(500);
				e.printStackTrace();
			}
			return writer;
			
		});
		
		Spark.post("/favorite_fruit", (request, response) -> {
			final String fruit = request.queryParams("fruit");
			if (fruit == null) {
				return "What don't you pick up one?";
			} else {
				return "Your favorite fruit is " + fruit;
			}
		});
	
	}

}
