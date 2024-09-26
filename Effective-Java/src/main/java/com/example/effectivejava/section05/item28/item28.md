## 📌 배열과 리스트 차이점

1. 배열은 공변(covariant) 리스트는 불공변(invariant)

   > 공변이란?
   >
   >
   > → `Sub` 타입이 `Super` 타입의 하위 타입이라면 `Sub[]` 타입이 `Super[]`의 하위 타입이 된다. (함께 변하는 것)
   >
    - 하지만 런타임 단계에서 `ArrayStoreException` 발생 가능
    - 리스트는 불공변, `List<Sub>`는 `List<Super>`로 변환할 수 없음
      → 컴파일 단계에서 타입 안전성을 보장해줌
2. 배열 타입은 런타임에 검사, 리스트는 컴파일에 검사
    - 배열은 **런타임에 타입 오류가 발생**, 잘못된 타입을 배열에 넣으면 실행중에 오류가 발생함
    - 리스트는 **컴파일에 타입을 검사**하므로, 잘못된 타입의 요소를 리스트에 넣으려고 하면 컴파일 오류가 발생하여 미리 문제를 발견할 수 있음
3. 제네릭과의 호환성
    - 배열은 **제네릭 타입 사용 불가**, 제네릭 타입 배열 생성 또한 금지
      리스트는 **제네릭 지원, 타입 안전성 보장**

### 배열 문제점

```java
public class ArrayCovarianceExample {
    public static void main(String[] args) {
        Object[] objectArray = new Long[1];  // 배열은 공변성을 가짐
        objectArray[0] = "I am not a Long";  // ArrayStoreException 발생
    }
}
```

- 컴파일 단계에서는 에러를 뱉지 않지만, 런타임 단계에서 에러를 발생시킴

### 리스트 사용 예시

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();  // 리스트는 불공변
        objectList.add("I am a String");
        objectList.add(123);  // 정수도 안전하게 추가 가능

        for (Object obj : objectList) {
            System.out.println(obj);  // 출력: I am a String, 123
        }

        // 타입 불일치 방지
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        // stringList.add(123);  // 컴파일 오류 발생: 타입 불일치
    }
}
```

- 컴파일 단계에서 타입 안정성을 보장해주어 다른 타입이 들어오는 경우 컴파일 오류 발생

### 제네릭 배열 생성 문제

- Java에서는 제네릭 배열을 직접 생성할 수 없음
  → 제네릭 타입은 타입 소거(Type Erasure) 때문에 런타임에 타입 정보를 유지하지 않음
  ⇒ 타입소거? : 런타임 단계에서 제네릭으로 작성한 타입들이 소거되는 것

> 제네릭 배열 사용 예시
>

```java
public class GenericArrayExample<T> {
    public void createArray() {
        // T[] array = new T[10];  // 컴파일 오류: 제네릭 배열 생성 불가
    }
}
```

> 제네릭 리스트 사용 예시
>

```java
import java.util.ArrayList;
import java.util.List;

public class GenericListExample<T> {
    public void createList() {
        List<T> list = new ArrayList<>();  // 제네릭 리스트는 생성 가능
        System.out.println("List created successfully!");
    }
}
```

---

### 정리

- 제네릭 타입을 사용할 때 발생하는 타입 안전성 문제를 피하기 위해 배열보다는 리스트를 사용
- ✅ 배열은 공변성을 가지므로 런타임 단계에서만 타입 불일치에 대한 오류를 알 수 있음
  ✅ 리스트는 불공변성을 가지며 컴파일 단계에서 타입을 검사하므로 타입 안전성 보장
- 제네릭 배열 생성이 허용되지 않으니, 제네릭 타입을 사용할 때 리스트를 사용하는것이 더 안전하고 유연함!!