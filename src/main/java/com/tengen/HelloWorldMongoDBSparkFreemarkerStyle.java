package com.tengen;

import java.io.StringWriter;
import java.net.UnknownHostException;

import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreemarkerStyle {
	public static void main(String[] args) throws UnknownHostException {
		
		// Freemarker configuration.
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
		
		// MongoDB DB/collection.
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");

		// Java 8 style (lambda expression).
		Spark.get("/", (request, response) -> {
			StringWriter writer = new StringWriter();
			try {
				Template helloTemplate = configuration.getTemplate("hello.ftl");
				
				DBObject document = collection.findOne();
				
				helloTemplate.process(document, writer);
				
			} catch (Exception e) {
				//halt(500);
				e.printStackTrace();
			}
			return writer;
		});
	
	}

}
