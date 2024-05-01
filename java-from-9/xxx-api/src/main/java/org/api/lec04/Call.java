package org.api.lec04;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Call {

	public static void main(String[] args) {
		callA();
	}

	private static void callA() {
		callB();
	}
	private static void callB() {
		callC();
	}
	private static void callC() {
		List<String> walk = StackWalker.getInstance()
			.walk(s -> s.map(StackWalker.StackFrame::getMethodName)
				.collect(Collectors.toList()));

		for (String s : walk) {
			System.out.println(s);
		}
	}
}
