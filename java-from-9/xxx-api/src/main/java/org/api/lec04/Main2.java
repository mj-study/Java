package org.api.lec04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class Main2 {
	public static void main(String[] args) throws Exception{
		Executor executor = CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS);
		Runnable sleep = () -> {
			System.out.println(System.currentTimeMillis() + " - 작업 완료");
		};
		System.out.println(System.currentTimeMillis() + " - 작업 실행");
		CompletableFuture<Void> future = CompletableFuture.runAsync(sleep, executor);

		future.get();
	}
}
