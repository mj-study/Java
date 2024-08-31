# 🚀 개요

<aside>
💡

접근 권한 최소화 하는 이유?
- `public`으로 선언할 경우 관리해야하는 범위가 넓어지고, 불필요한 부분까지 신경써야함
- 내부 구현과`API` 를 분리하여 설계 → 관심사에 맞는 패키지별로 나누고, 해당 기능들을 관리

</aside>

## 📌 캡슐화(정보 은닉) 이점

- 시스템 개발 속도 🔼 , 여러 컴포넌트를 병렬로 개발 가능
- 시스템 관리 비용 🔽 → 각 컴포넌트를 더 빨리 파악하고 디버깅이 가능, 다른 컴포넌트로 교체하는 비용도 적음
- 캡슐화 자체가 성능을 높이지는 않지만 성능 최적화에 도움을 줌
  → 다른 컴포넌트 영향 없이 해당 컴포넌트만 수정하면 되기때문
- 재사용성 🔼
- 규모가 큰 시스템을 분할하여 개발하기 때문에 난이도 🔽 (MSA 구조도 이에 속함)
- **응집도 증가**: 관련된 코드가 하나의 모듈(클래스)에 모이고, 이를 통해 코드의 응집도 증가
- **결합도 감소**: 클래스 간의 의존성을 줄여, 한 클래스의 변경이 다른 클래스에 미치는 영향을 최소화
- **유지 보수성 향상**: 외부에서 접근 가능한 멤버가 줄어들어 코드의 수정이 용이해집니다.

## 📌 접근 권한 제어자 (Access Modifiers)

1. **`private`**: 같은 클래스 내에서만 접근 가능
2. **`package-private`** (`default` 기본 접근 수준, 명시적 제어자 없음): 같은 패키지 내의 클래스에서 접근 가능
3. **`protected`**: 같은 패키지 내의 클래스나 상속받은 클래스에서 접근 가능
4. **`public`**: 모든 클래스에서 접근 가능

<aside>
✅

클래스와 멤버의 접근 권한을 설정할 때는 가능한 한 가장 제한적인 수준을 사용해야 함
→ 일반적으로 `private`을 사용하고, 필요할 때만 접근 권한을 높이는 것이 좋다

</aside>

## 📌 접근 권한 최소화 가이드라인

### 클래스의 접근 권한 최소화

- **Top-Level 클래스**: 최상위 클래스는 거의 항상 `public`이나 `package-private` 이어야 한다.
  `public`으로 설정하면 해당 클래스를 모든 패키지에서 사용할 수 있고, `package-private`으로 설정하면 같은 패키지에서만 사용이 가능
- **Inner 클래스**: 가능하면 `private`이나 `package-private`으로 설정하여 외부에 노출되지 않도록 해야 함

### 멤버의 접근 권한 최소화

- **멤버 변수**: 가능한 `private`으로 선언하고, 필요한 경우에만 `getter` 메서드를 제공하여 접근을 허용
- **메서드**: 메서드 또한 가능한 `private`으로 설정하고, 외부에서 호출해야 하는 메서드만 `public`으로 설정

### 접근 권한 최소화의 예시

> ❌ 예시 1: 잘못된 접근 권한 설정
>

```java
// 나쁜 예: 모든 멤버가 public으로 노출되어 있음
public class Point {
    public double x; // -> 멤버를 굳이 public으로 노출 할 필요x, getter 등의 메서드를 통해 제공
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

```

위 코드는 `Point` 클래스의 멤버 변수 `x`와 `y`가 `public`으로 선언되어 외부에서 자유롭게 접근하고 수정할 수 있습니다. 이 경우, 객체의 불변성을 보장할 수 없으며, 캡슐화 원칙이 깨집니다.

> ⭕ 예시 2: 접근 권한을 최소화한 설계
>

```java
// 좋은 예: 멤버 변수를 private으로 설정하고, 필요한 경우에만 접근자 제공
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}

```

위 코드는 `x`와 `y`를 `private`으로 선언하여 외부에서 직접 접근하지 못하도록 했습니다. 또한, 객체의 상태를 변경하는 메서드인 `move`를 제공하여 객체의 불변성을 유지하면서도 기능을 확장할 수 있습니다.

## 📌 상수 선언

- 상수 선언시 `public static final`을 활용하여 작성하되, 배열같이 내부에 접근하여 값을 수정할 수 있는 경우를 주의!

```java
package com.example.effectivejava.section04.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CafeConstant {

	private static class CafeInfo {
		public CafeInfo(String menu, int price) {
			this.menu = menu;
			this.price = price;
		}

		private String menu;
		private int price;
	}

	public static final String AVAILABLE_ROLE = "super";
	// 배열에 접근하여 값을 바꿀 수 있는 상수는 사용x
	public static final CafeInfo[] INCORRECT_CAFE_INFOS = {new CafeInfo("iceAmericano", 5000)};

	private static final CafeInfo[] CORRECT_CAFE_INFOS = {new CafeInfo("iceAmericano", 5000)};
	/*
	방법1. 배열을 private 으로 만들고 배열을 public 불변 리스트에 추가
	 */
	public static final List<CafeInfo> VALUE_LIST = Collections.unmodifiableList(Arrays.asList(CORRECT_CAFE_INFOS));

	/*
	방법2. 배열을 private 으로 만들고 복사본 반환하는 public 메서드 추가 (방어적 복사)
	 */
	public static final CafeInfo[] getCafes() {
		return CORRECT_CAFE_INFOS.clone();
	}

}
```

### `Collections.unmodifiableList`?

- 불변 리스트를 생성하는 메서드
  → 주어진 리스트의 내용을 수정할 수 없는 읽기 전용 리스트를 반환
  → 원본 리스트의 요소를 보호하고, 외부에서 리스트에 대한 변경을 방지하기 위해 사용
- `public static <T> List<T> unmodifiableList(List<? extends T> list)`
    - 매개변수 list : 수정이 불가능한 리스트로 만들 원본
    - 반환값 : 읽기 전용 리스트
- 사용 목적 & 이점
    1. 리스트 불변성 보장 : 원본 리스트의 내용을 보호하기 위해 사용
       → 메서드가 리스트를 반환할 때, 반환된 리스트가 외부에서 변경되지 않도록 하여 내부 구현 보호
    2. 완전한 데이터 공유 : 여러 스레드가 동일한 리스트를 사용할 때, 해당 메서드를 사용하여 동기화 문제를 방지하고 , 리스트 불변 보장
    3. 의도 명확화 : 메서드 설계 시 수정되지 않는 리스트임을 명확하게 알려줌

<aside>
⚠️

주의 사항

- 얕은 복사 : 메서드로 생성된 리스트는 원본 리스트의 얕은 복사이므로 원본이 변경되면 불변 리스트도 변경
- 예외 발생 : 불변 리스트에 변경 작업 시도할 경우
  → `UnsupportedOperationException` 예외 발생
- 원본 리스트 관리 필요 : 원본 리스트는 변경 가능하기에, 불변 리스트의 불변성을 보장하려면 원본 리스트 변경도 신중히 관리해야 함
</aside>

## 📌 정리

- 접근 권한을 최소화하는 것은 유지보수 가능한 코드 작성의 핵심
  → 가능한 한 `private`로 접근을 제한하고, 외부에 필요한 최소한의 인터페이스만을 공개하는 것이 좋다.
  → 이렇게 하면 클래스 간의 결합도를 낮추고, 예기치 않은 변경으로 인한 오류를 줄일 수 있음
  → 이를 통해 코드의 안정성과 확장성을 높일 수 있습니다.