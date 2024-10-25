### 문제점

- 제네릭과 가변인수를 함께 사용하면 타입 안정성이 깨질 수 있음
  → ex) 가변인수 배열의 타입이 제네릭일 경우, heap 오염 발생 가능
  → 컴파일러가 타입 경고를 표시함, 이를 무시하면 런타임 에러를 유발할 수 있음

### 코드 예시

```java

@SafeVarargs
static <T> List<T> flatten(List<T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<T> list : lists) {
        result.addAll(list);
    }
    return result;
}
```

- 위 코드에서 가변인수 배열 `List<T>... lists` 타입 안정성 경고 발생
  → 힙 오염 가능성
  → `@SafeVarargs` 애노테이션을 추가하여 메서드가 타입 안전하다는 확신을 줄 수 있지만, 무조건 사용해서는 안됨

### 문제 발생 예시

```java
static void dangerous(List<String>... stringLists) {
    Object[] array = stringLists;  // 힙 오염 발생 가능
    array[0] = List.of(42);        // List<String>이 아닌 List<Integer> 삽입
    String s = stringLists[0].get(0); // 런타임 오류 발생 가능
}
```

- `List<String>` 에 `List<Integer>`가 들어가 힙 오염 발생

### 해결 방법

- 제네릭과 가변인수를 안전하게 사용하려면 `@SafeVarargs` 애노테이션을 사용할 수 있음
  → 타입 안전성이 보장된 경우에만 사용

1. `@SafeVarargs` 애노테이션 추가
2. 가변인수를 내부에서 배열로 사용하지 않기

### 안전한 코드 예시

```java

@SafeVarargs
static <T> List<T> safeFlatten(List<? extends T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists) {
        result.addAll(list);
    }
    return result;
}
```

- `List<? extends T>`를 사용하여 힙 오염을 방지하고, 가변인수 배열을 사용하지 않음

### 정리

- 제네릭과 가변인수를 함께 사용할 때는 타입 안정성에 신경써야함
- 힙 오염이 발생할 가능성이 있고, 이를 방지하기 위해 `@SafeVarargs` 애노테이션 사용가능
- 가변인수를 배열이 아닌 리스트로 변환하여 안전하게 처리하는 방법이 권장됨