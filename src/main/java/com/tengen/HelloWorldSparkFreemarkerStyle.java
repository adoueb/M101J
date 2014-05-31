package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreemarkerStyle {
	public static void main(String[] args) {
		
		// Freemarker configuration.
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

		// Java 8 style (lambda expression).
		Spark.get("/", (request, response) -> {
			StringWriter writer = new StringWriter();
			try {
				Template helloTemplate = configuration.getTemplate("hello.ftl");
				Map<String, Object> helloMap = new HashMap<String, Object>();
				helloMap.put("name", "Freemarker");
				
				helloTemplate.process(helloMap, writer);
				
			} catch (Exception e) {
				//halt(500);
				e.printStackTrace();
			}
			return writer;
		});
	
	}


}
