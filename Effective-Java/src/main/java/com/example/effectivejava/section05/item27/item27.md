### Java에서 제네릭 사용시 비검사 경고

<aside>
💡

비검사 경고란(unchecked warning)?

제네릭 타입을 사용하는 과정에서 **`컴파일러가 타입 안정성을 완전히 보장할 수 없을 때`** 발생하는 경고

발생예시) Raw 타입 사용, 제네릭 배열 생성, 타입 캐스팅 등

</aside>

- 비검사 형변환 경고
- 비검사 메서드 호출 경고
- 비검사 매개변수화 가변인수 타입 경고, 비검사 변환 경고 등
- 많은 부분에서 비검사 경고가 발생함, 제네릭에 익숙해질수록 마주치는 경고수는 🔽
- 이러한 경고는 **잠재적인 타입 안정성 문제를 나타내므로** 경고를 무시하지말고!
  해결하는것이 중요

### 비검사 경고 문제점

- 런타임 예외 발생 위험 : 비검사 경고는 컴파일러가 타입 정보를 완전히 확인하지 못하기에, 잘못된 타입 사용이 런타임에서 `ClassCastException` 을 발생시킬 수 있음
- 타입 안전성 손상 : 경고를 무시하면 제네릭의 가장 큰 장점인 **타입 안정성(type safety)**을 잃게 됨
- 코드 가독성 저하 : 비검사 경고를 무시하면 코드가 복잡해지고, 다른 개발자가 코드를 읽을 때 혼란을 줄 수 있음

### 비검사 경고 제거하는 방법

1. 제네릭 타입을 정확히 사용
    - 비검사 경고가 발생하는 대부분의 경우, 제네릭 타입을 올바르게 사용하여 경고를 제거할 수 있음
    - Raw 타입 대신 제네릭 타입을 사용하는 것이 좋음
2. 타입 안전한 캐스팅 사용
    - 불가피하게 타입 캐스팅이 필요할 때는 타입 안전성을 보장할 수 있는 방식으로 캐스팅해야함
3. `@SuppressWarnings("unchecked")` 사용
    - 경고를 완전히 제거할 수 없는 경우, 정말 필요한 상황에 한해 `@SuppressWarnings("unchecked")`어노테이션을 사용하여 경고를 무시할 수 있음
    - 이 어노테이션은 가급적 마지막 수단으로만 사용해야 하고,
      비교적 작은 범위 (지역변수 또는, 메서드)에 작성을 권장하고 (클래스 전체에 작성은 ❌)
      적절한 설명과 함께 사용해야함

## 📌 코드 예시

### 비검사 경고 발생 코드

```java
public class UncheckWarningExam {
	public static void main(String[] args) {
		// RAW 타입 사용 -> 비검사 경고 발생
		List list = new ArrayList();
		list.add("테스트");
		list.add(123);

		list.forEach(System.out::println);
	}
}
```

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/ea4a33e0-958d-4ee3-a09d-3d351c98a753/45524c0c-c353-47aa-b5fd-140bf15f7fc5/image.png)

### 제네릭 사용하여 타입 안전성 보장

```java
// 제네릭 타입 사용 -> 타입 안전성 보장
		List<String> stringList = new ArrayList<>();
		stringList.add("테스트");
		
		stringList.forEach(System.out::println);
```

### `@SuppressWarning("unchecked")` 어노테이션 사용

```java
List<?> rawList = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<String> stringList1 = (List<String>)rawList; // 비검사 형변환, 불가피한 경우에만 어노테이션 사용, 코드가 안전하다는 확신이 있을때!
		
		stringList1.add("하이");
		System.out.println(stringList1.get(0)); // 정상 출력
```

### 정리

- 비검사 경고는 Java에서 제네릭을 사용할 때 타입 안전성을 보장할 수 없을때 발생
- 비검사 경고를 제거하는 것이 중요, 이를 통해 코드의 타입 안전성을 확보
- Raw 타입 대신 제네릭 타입을 사용하고, 타입 캐스팅을 최소화하고, 필요한 경우에만 `@SuppressWarning("unchecked")` 어노테이션 사용
- 비검사 경고를 무시하는 대신, 경고를 제거하고 타입 안전성을 높이는 것이 좋은 코드 품질을 유지하는 방법 !!!