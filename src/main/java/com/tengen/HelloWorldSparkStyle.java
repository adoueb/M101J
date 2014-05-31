package com.tengen;

import spark.Spark;

public class HelloWorldSparkStyle {
	public static void main(String[] args) {
		
		// Java 8 style (lambda expression).
		Spark.get("/", (request, response) -> "Hello World from Spark!!\n");
		
		Spark.get("/test", (request, response) -> "This is a test page\n");
		
		Spark.get("/echo/:thing", (request, response) -> request.params(":thing"));
	
		// Java 7 style.
//		Spark.get(new Route("/") {
//			@Override
//			public Object handle(final Request request, final Response response) {
//				//return new String("Hello World from Spark!!");
//				return null;
//			}	
//		});
	}

}
