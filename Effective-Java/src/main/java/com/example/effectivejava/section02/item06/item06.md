- 똑같은 기능의 객체를 매 번 생성하는것보다 객체 하나를 재사용하는게 나을 때가 많음

```java
// 아래와 같은 작성은 금지
String s = new String("화이트");

// 위 코드 개선
String s = "화이트";
```

- 생성자 대신 팩토리 메서드를 제공하는 불변 클래스에서는 불필요한 객체 생성을 피할 수 있음
- 생성 비용이 비싼 객체가 있는데 비싼 객체를 계속 사용한다면 캐싱하여 재사용하는 것을 권장

    ```java
    public class RomanNumerals {
    
    	/*
    	아래 메서드는 유효한 로마 숫자인지 확인하는 정규 표현식을 간단하게 작성한 경우인데
    	반복해서 호출할 경우 성능이 저하될 수 있음
    	# 불필요한 객체 만드는 예시1
    	*/
    	static boolean isRaomanNumeralV1(String s) {
    		return s.matches("^[IVXLVL]$");
    	}
    
    	// static 에 캐싱해두고 나중에 isRomanNumeralV2 메서드가 호출될때마다 인스턴스를 재사용함
    	// => 성능 상당 개선효과
    	private static final Pattern ROMAN = Pattern.compile("^[IVXLVL]$");
    	
    	static boolean isRomanNumeralV2(String s) {
    		return ROMAN.matcher(s).matches();
    	}
    
    }
    ```

    - 객체가 불변이면 재사용해도 안전함
      하지만 훨씬 더 명확하거나 직관에 반대되는 경우도 있음

        > 💡 어댑터(뷰)  
        → 실제 작업은 뒷단 객체에 위임  
        → 자신은 제 2의 인터페이스 역할을 해주는 객체   
        → 뒷단 객체 외엔 관리할 상태가 없으므로 뒷단 객체 하나당 어댑터 하나씩만 만들면 됨  
    
        - 예를 들어 `Map` 인터페이스의 `keySet` 메서드는 Map 객체 안의 키 전부를 담은 Set 뷰를 반환함
        - `keySet` 을 호출할 때마다 새로운 인스턴스가 생성된다 생각할 수 있지만, 매번 같은 Set 인스턴스를 반환할지도 모름
          ⇒ `Set` 인스턴스가 가변이더라도 반환된 인스턴스들은 기능적으로 모두 같음
          ⇒ 즉, 반환한 객체를 수정하더라도 다른 모든 객체가 같이  바뀜 (모두가 같은 `Map` 인스턴스를 대변하기 떄문)


#### 불필요한 객체 만드는 예시2

> 💡 오토박싱은 기본 타입과 그에 대응하는 박싱된 기본 타입의 구분을 흐려주지만, 완전히 없애주는것은 아님  
→ 의미상으로는 별다를 것 없지만 성능에서는 그렇지 않음


```java
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
```

- 오토박싱을 했을때와 그렇지 않은 경우 성능 차이가 많이 난다.
- **박싱된 기본 타입보다는 기본 타입을 사용하고 의도치 않은 오토박싱이 숨어들지 않도록 주의!**

✅ 다만, 방어적 복사가 필요한 경우는 기존 객체를 재사용하는하지 말아야함 ⇒ 버그로 이어지는 지름길

상황에 따라 사용하자