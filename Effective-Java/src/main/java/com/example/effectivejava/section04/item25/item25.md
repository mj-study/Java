### 탑 레벨 클래스란?

- 파일 내에 정의된 가장 바깥쪽의 클래스를 의미
- Java에서 탑 레벨 클래스는 반드시 `public`, `default` 접근 제어중 하나여야함
- 일반적으로 탑 레벨 클래스는 `.java` 파일과 1:1로 대응됨

### 탑 레벨 클래스를 한 파일에 하나만 포함해야 하는 이유

- 가독성과 유지보수성
    - 한 파일에 여러 개의 탑 레벨 클래스를 포함하면, 파일이 복잡해지고 코드를 읽기 어려워짐
    - 유지보수가 어렵고, 클래스의 역할과 책임을 명확하게 구분하기 어려움
- 예측 가능성
    - 파일 이름과 클래스 이름이 일치하는 것이 일반적인 Java 규칙
    - 한 파일에 여러 개의 탑 레벨 클래스가 포함되면, 해당 파일이 제공하는 기능을 예측하기 어려움
- 컴파일러의 제약
    - 한 파일에 두 개 이상의 `public` 탑 레벨 클래스를 정의하면 컴파일 오류가 발생
      → Java 컴파일러가 파일 이름과 클래스 이름을 일치시키기 때문
    - `default` 접근 제어자를 사용할 때는 컴파일러가 오류를 발생시키지는 않지만, 코드의 가독성과 유지보수성을 떨어뜨림
- 클래스 파일의 중복 및 충돌 위험
    - 한 파일에 여러 개의 탑 레벨 클래스가 있는 경우, 다른 개발자가 해당 파일을 수정하거나 사용할 때 의도하지 않은 클래스 파일이 컴파일되거나, 중복된 클래스가 생길 수 있음

### 잘못된 예시 (한 파일에 여러 개 탑 레벨 클래스)

```java
// Utensils.java

class Utensil {
    static final String NAME = "pan";
}

class Dessert {
    static final String NAME = "cake";
}
```

- 가독성 저하 → 어떤 클래스가 어느 책임을 가지는지 명확하지 않음
- 예측 불가 → `Utensil` 파일명만 보고 `Utensil` 클래스 외에 `Dessert`라는 클래스가 포함되어 있을 것이라고 예상하기 어려움
- 컴파일 문제 → 두 클래스 모두 `public`으로 선언되면 컴파일 오류발생
  ✅ 만약 `default` 접근 제어자를 사용하면, 클래스가 의도하지 않은 곳에서 사용될 수 있음

### 정리

- 탑 레벨 클래스를 한 파일에 담지말고, 분리해서 작성할 것
- 굳이 여러 탑 레벨 클래스를 한 파일에 담고 싶다면 정적 멤버 클래스를 사용하는 방법을 고민
  → `private` 으로 선언하면 접근 범위를 최소한으로 관리하면서 읽기에도 좋음

    ```java
    public class Test {
    	public static void main(String[] args) {
    		System.out.println(Utensil.NAME + Dessert.NAME);
    	}
    	
    	private static class Utensil {
    		static final String NAME = "pan";
    	}
    	
    	private static class Dessert {
    		static final String NAME = "cake";
    	}
    }
    ```