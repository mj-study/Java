package com.example.effectivejava.section02.item06;

import java.util.concurrent.TimeUnit;

public class AutoBoxing {
	private static void sum() {
		long start = System.currentTimeMillis();
		Long sum = 0L;
		for (long i = 0; i <= Integer.MAX_VALUE; i++) {
    		sum += i;
    	}
		long end = System.currentTimeMillis();
		System.out.printf("오토박싱o 걸린시간 %d초 \n", TimeUnit.MILLISECONDS.toSeconds(end - start)); // 2.xx 초
	}

	private static void sum2() {
		long start = System.currentTimeMillis();
		long sum = 0L;
		for (long i = 0; i <= Integer.MAX_VALUE; i++) {
			sum += i;
		}
		long end = System.currentTimeMillis();
		System.out.printf("오토박싱x 걸린시간 %d 초  \n", TimeUnit.MILLISECONDS.toSeconds(end - start)); //  0.xx 초
	}

	public static void main(String[] args) {
		sum();
		sum2();
	}
}
