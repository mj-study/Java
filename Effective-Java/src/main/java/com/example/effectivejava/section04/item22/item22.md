## 📌 인터페이스 구현하는 의미

- 인터페이스를 구현한다는 것은 자신의 인스턴스로 무엇을 할 수 있는지 클라이언트에게 알려주는 행위
    - 인터페이스는 오직 이 용도로만 사용해야 한다

## 📌 인터페이스를 잘못 사용하고 있는 예

- 인터페이스에서 메서드 없이 상수 필드로만 가득 채워 넣고 사용

```java

/**
 * 인터페이스를 잘못 사용한 예시
 * 오로지 상수 정보 제공만을 목적으로 메서드 없이 정적 필드 제공
 */
public interface IncorrectInterface {
	static final String USER_NAME = "상수";
	static final int USER_CODE = 123_333_555;
}
```

- 위의 코드는 상수 인터페이스 안티패턴으로 인터페이스를 잘못 사용한 예시다
    - 상수 인터페이스 안티패턴?
      → 잘못된 설계 패턴의 하나, 인터페이스를 오직 상수를 정의하는 용도로만 사용하는 것
      → Java에서 안티패턴으로 간주됨
      → 코드의 가독성과 유지보수성을 떨어뜨릴 수 있음

## 📌 상수 설계 좋은 예시

- 특정 클래스나 인터페이스와 강하게 연관된 상수이면 해당 클래스나 인터페이스 자체에 상수를 추가하자
  → 대표적으로 `Integer`, `Double`에 선언된 `MIN_VALUE`, `MAX_VALUE`가 해당 예시임
- 열거 타입으로 나타내기 적합하다면 `enum`을 사용하자

### 정적 클래스

```java
public class StaticClassConstant {
	// 인스턴스화 방지
	private StaticClassConstant(){};
	public static final double PI = 3.141592;
	public static final String TYPE = "기본 타입";
}
```

```java
import static com.example.effectivejava.section04.item22.StaticClassConstant.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StaticClassConstantTest {
	
	@Test
	void test_constant () {
	    //given
	    // StaticClassConstant.PI 로 접근
	    // StaticClassConstant를 static import 하여 사용하면 더 간결해짐
		double pi = PI; 
		String type = TYPE;
	}

}
```

### 열거형 `enum` 사용

```java
/**
 * 연관되어 있는 상수 제공시 enum 타입을 이용
 */
public enum Days {
	SUN("일요일", 0),
	MON("월요일",1),
	TUE("화요일", 2),
	WED("수요일", 3),
	THU("목요일", 4),
	FRI("금요일", 5),
	SAT("토요일", 6),
	;

	private final String day;
	private final int dayCode;

	Days(String day, int dayCode) {
		this.day = day;
		this.dayCode = dayCode;
	}

	public String getDay() {
		return day;
	}

	public int getDayCode() {
		return dayCode;
	}
}
```