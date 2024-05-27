- 자바 라이브러리에는 `close` 메서드를 호출해 직접 닫아줘야 하는 자원이 많음
    - `InputStream, OutputStream, (sql) Connection` 등
- 전통적으로 객체를 닫는 수단으로 `try-finally`가 쓰임

```java
	/*
	 * try-finally 이용해서 자원 닫기
	 * */
	static String firstLineOfFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			return br.readLine();
		} finally {
			br.close();
		}
	}

	/*
	* 자원이 2개 이상 닫아야 한다면 코드가 지저분해짐
	* */
	static void copy(String src, String dst) throws IOException {
		InputStream in = new FileInputStream(src);
		try {
    		OutputStream out = new FileOutputStream(dst);
    		try {
    			byte[] buf = new byte[8192];
    			int n;
    			while ((n = in.read(buf)) >= 0)
    				out.write(buf, 0, n);
    		} finally {
    			out.close();
    		}
    	} finally {
    		in.close();
    	}
	}
```

- 위의 `firstLineOfFile` 메서드의 `readLine` 에서 예외가 발생하면  `close` 도 실패할 것이다.
    - 이런 상황이면 두 번째 예외가 첫 번재 예외를 삼켜 스택 추적에 어려움을 준다.
- 이런 문제들은 Java 7 에서 나온 `try-with-resources` 에 의해 해결됨
    - 이 구조를 사용하려면 해당 자원이`AutoCloseable` 인터페이스를 구현해야함

> try-with-resource 사용
>

```java
	static String firstLineOfFile(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			return br.readLine();
		}
	}

	static void copy(String src, String dst) throws IOException {
		try (InputStream in = new FileInputStream(src);
			 OutputStream out = new FileOutputStream(dst)
		) {
			byte[] buf = new byte[8192];
			int n;
			while ((n = in.read(buf)) >= 0)
				out.write(buf, 0, n);
		}
	}
```

- 코드가 간결해지고 문제를 진단하기도 좋아졌음
- `fistLineOfFile` 메서드의 `readLine` 와 `close` 에서 예외가 발생하면 `close`에서 발생한 예외는 숨겨지고 `readLine`에서 발생한예외가 기록됨
  ⇒ 이렇게 숨겨진 예외도 그냥 버려지지 않고 `suppressed`(숨겨졌다라는 의미)를 달고 출력됨

✅ 꼭 회수해야할 자원이라면 `try-with-resources` 사용하기!