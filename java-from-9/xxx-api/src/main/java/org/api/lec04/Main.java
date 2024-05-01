package org.api.lec04;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception {
		List<Integer> nums = List.of(1, 2);
		// nums.add(3);
		// nums.remove(0);

		// 10초간 기다렸다 작업 완료를 출력하는 Runnable
		Runnable sleep = () -> {
			try {
				Thread.sleep(10_000L);
				System.out.println(System.currentTimeMillis() + " - 작업 완료");
			} catch (InterruptedException e) {

			}
		};

		System.out.println(System.currentTimeMillis() + " - 작업 실행");
		CompletableFuture<Void> future = CompletableFuture.runAsync(sleep)
			// .orTimeout(1, TimeUnit.SECONDS);
			.completeOnTimeout(null, 1, TimeUnit.SECONDS);

		// 실행시킨 비동기 작업이 완료될 때까지 현재 스레드를 멈추는 기능
		future.get();
	}
}
