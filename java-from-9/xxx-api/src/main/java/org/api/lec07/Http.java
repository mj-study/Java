package org.api.lec07;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();

		// var request = HttpRequest.newBuilder(URI.create("https://postman-echo.com/get"))
		// 	.GET()
		// 	.build();
		// HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// System.out.println(response.statusCode());
		// System.out.println(response.body());

		// 비동기적 방식
		// client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
		// 	.thenAccept((response) -> {
		// 		printlnWithThread(response.statusCode());
		// 		printlnWithThread(response.body());
		// 	})
		// ;

		var request = HttpRequest.newBuilder(URI.create("https://postman-echo.com/post"))
			.POST(HttpRequest.BodyPublishers.ofString("{\"num\":1}"))
			.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		printlnWithThread(response.statusCode());
		printlnWithThread(response.body());

		client.close();
	}

	private static void printlnWithThread(Object obj) {
		System.out.printf("%s %s\n", Thread.currentThread().getName(), obj);
	}
}
