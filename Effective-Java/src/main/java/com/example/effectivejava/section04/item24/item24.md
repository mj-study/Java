## 📌 멤버 클래스

### 정적 멤버 클래스 (static member class)

- static 키워드로 선언된 클래스
- 외부 클래스의 인스턴스와 독립적으로 동작할 수 있음

### 비정적 멤버 클래스 (non-static member class OR inner class)

- static 키워드가 없는 클래스
- 외부 클래스의 인스턴스와 연결되며 외부 클래스의 모든 인스턴스 필드와 메서드에 접근할 수 있음

### 익명 클래스 (anonymous class)

- 이름이 없는 클래스, 클래스 정의와 동시에 인스턴스를 생성
  → 주로 인터페이스나 추상클래스의 인스턴스를 즉석에서 만들 때 사용

### 지역 클래스 (local class)

- 메서드나 초기화 블록 내부에 정의된 클래스, 해당 블록 범위 안에서만 사용 가능

## 📌 비정적 멤버 클래스 문제점

- 외부 클래스의 인스턴스와 암묵적으로 연결됨, 이로 인해 발생되는 문제점
- 메모리 누수
    - 비정적 멤버 클래스는 외부 클래스의 인스턴스에 대한 숨겨진 참조를 유지함
      → 외부 클래스 인스턴스가 더 이상 필요하지 않더라도 G.C에 의해 수거되지 않을 수 있음
      ⇒ 메모리 누수를 초래 할 수 있음
- 성능 저하
    - 비정적 멤버 클래스는 외부 클래스의 인스턴스와 결합되어 있기에, 클래스의 인스턴스를 생성할 때 외부 클래스의 인스턴스가 필요함
      → 추가적인 메모리와 성능 비용 유발
- 캡슐화 약화
    - 비정적 멤버 클래스는 외부 클래스의 모든 인스턴스 멤버에 접근 가능
      → 의도하지 않은 접근이나 부작용 발생 가능성
      ⇒ 클래스의 캡슐화를 약화 시킴

## 📌 정적 멤버 클래스 장점

- 메모리 누수 방지
    - 정적 멤버 클래스는 외부 클래스의 인스턴스에 대한 참조 유지 ❌
      → 외부 클래스 인스턴스와 독립적으로 존재 가능
      ⇒ 메모리 누수 방지 도움
- 성능 향상
    - 정적 멤버 클래스는 외부 클래스의 인스턴스와 결합 ❌
      → 인스턴스 생성 시 더 적은 메모리와 성능 비용이 듦
- 더 나은 캡슐화
    - 정적 멤버 클래스는 외부 클래스의 인스턴스 멤버에 접근 ❌
      → 강력한 캡슐화 제공 및 클래스 간의 결합을 줄여줌
- 외부 클래스와 독립적인 사용 가능
    - 독립적으로 사용이 가능하며, 유틸리티 클래스처럼 사용할 수도 있음

## 📌 예시 코드

> 비정적 멤버 클래스
>

```java
public class OuterClass {
	private int outerField = 10;
	
	// 비정적 멤버 클래스
	public class InnerClass {
		public void printOuterField() {
			// 외부 클래스 인스턴스 필드 접근 가능
			System.out.println("Outer field : " + outerField);
		}
	}
	
	public static void main(String[] args) {
		OuterClass outer = new OuterClass();
		// 외부 클래스 통해야 내부 클래스 생성 가능
		OuterClass.InnerClass inner = outer.new InnerClass();
		inner.printOuterField();
	}
}
```

- 위 코드는 `OuterClass`와 `InnerClass` 의 인스턴스가 암묵적으로 결합되어 있음
  → `InnerClass` 인스턴스를 생성하려면 외부 클래스 `OuterClass` 인스턴스가 필요함
  ⇒ 외부 클래스 인스턴스가 필요 없을 때도 메모리 누수 & 성능 저하 발생 가능

> 정적 멤버 클래스
>

```java
public class OuterClass {
    private int outerField = 10;

    // 정적 멤버 클래스
    public static class StaticInnerClass {
        public void printMessage() {
            System.out.println("Hello from the static inner class!");
        }
    }

    public static void main(String[] args) {
        // StaticInnerClass는 외부 클래스의 인스턴스 없이 생성 가능
        OuterClass.StaticInnerClass staticInner = new OuterClass.StaticInnerClass();
        staticInner.printMessage(); // 출력: Hello from the static inner class!
    }
}
```

- 외부 클래스와 내부 클래스 인스턴스가 결합되지 않으므로 독립적으로 사용가능
  → 메모리 누수나 성능 문제 발생 ❌

> 익명 클래스
>

```java
import java.util.Comparator;
import java.util.Arrays;

public class AnonymousClassExample {
    public static void main(String[] args) {
        // 배열을 정렬할 Comparator를 익명 클래스로 정의
        Comparator<String> stringLengthComparator = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        String[] names = {"Alice", "Bob", "Christina", "Dave"};
        
        // 익명 클래스로 정의된 Comparator를 사용하여 배열 정렬
        Arrays.sort(names, stringLengthComparator);

        // 정렬된 결과 출력
        System.out.println(Arrays.toString(names)); // 출력: [Bob, Dave, Alice, Christina]
    }
}
```

- 일회성으로 간단한 구현 제공할 때 **`익명 클래스`** 유용

> 지역 클래스
>

```java
public class LocalClassExample {
    public static void main(String[] args) {
        // 메서드 내부에서 지역 클래스를 정의
        class MessagePrinter {
            private String message;

            public MessagePrinter(String message) {
                this.message = message;
            }

            public void printMessage() {
                System.out.println("Message: " + message);
            }
        }

        // 지역 클래스의 인스턴스 생성 및 사용
        MessagePrinter printer = new MessagePrinter("Hello from the local class!");
        printer.printMessage(); // 출력: Message: Hello from the local class!
    }
}
```

- 지역 클래스 → 메서드 내부나 블록 내에 정의된 이름이 있는 클래스
- 일반적으로 메서드나 블록 내에서 특정 로직을 캡슐화할 때 사용 (보통은 잘 사용안함)