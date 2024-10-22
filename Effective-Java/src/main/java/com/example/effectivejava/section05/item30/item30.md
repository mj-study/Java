### 제네릭 메서드 필요성

- 타입 안전성 : 제네릭 메서드를 사용하면 컴파일 타임에 타입 검사 가능
  → 타입 불일치로 인한 런타임 오류 방지
- 재사용성 : 메서드를 다양한 타입에서 사용할 수 있기에 중복된 코드를 줄일 수 있음
- 유연성 : 메서드를 호출할 때 타입을 지정하지 않아도, 컴파일러가 타입 추론을 통해 적절한 타입을 결정함

### 제네릭 메서드 정의 방법

```java
public <T> T methodName(T param) {
    // 메서드 내용
}
```

### 제네릭 메서드 예시

1. 최댓값 구하는 제네릭 메서드

    ```java
    public class GenericMethodExample {
    
        // 제네릭 메서드: 배열에서 최대값을 구함
        public static <T extends Comparable<T>> T max(T[] array) {
            if (array == null || array.length == 0) {
                throw new IllegalArgumentException("Array must not be empty.");
            }
    
            T maxElement = array[0];
            for (T element : array) {
                if (element.compareTo(maxElement) > 0) {
                    maxElement = element;
                }
            }
            return maxElement;
        }
    
        public static void main(String[] args) {
            Integer[] intArray = {1, 3, 2, 5, 4};
            String[] strArray = {"apple", "banana", "pear"};
    
            // 제네릭 메서드 호출
            Integer maxInt = max(intArray);
            String maxStr = max(strArray);
    
            System.out.println("Max Integer: " + maxInt); // Max Integer: 5
            System.out.println("Max String: " + maxStr); // Max String: pear
        }
    }
    ```

    - 한정적 와일드 카드를 이용하여 `Comparable` 구현한 클래스만 사용할 수 있음
      → 비교 가능한 객체에 대해서만 `max` 메서드 사용 가능
    - 제네릭 메서드는 정수 배열, 문자열 배열 모두에서 사용 가능
      → 메서드 호출 시 별도로 타입 지정 필요❌ ⇒ 컴파일러가 타입 추론
2. 리스트를 배열로 변환하는 제네릭 메서드

    ```java
    import java.util.List;
    
    public class GenericMethodExample {
    
        // 제네릭 메서드: 리스트를 배열로 변환
        public static <T> T[] toArray(List<T> list, T[] a) {
            if (a.length < list.size()) {
                // 배열이 작으면 리스트 크기에 맞는 새 배열을 생성
                return (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), list.size());
            }
            return list.toArray(a);
        }
    
        public static void main(String[] args) {
            List<String> stringList = List.of("apple", "banana", "cherry");
            String[] stringArray = new String[stringList.size()];
    
            // 제네릭 메서드 호출
            stringArray = toArray(stringList, stringArray);
    
            for (String str : stringArray) {
                System.out.println(str);  // apple, banana, cherry 출력
            }
        }
    }
    ```

    - 리스트 타입에 관계없이 제네릭 메서드를 통해 타입 안전한 변환 가능

### 제네릭 메서드 사용 시 주의 사항

- 타입 제한(bound)
    - 제네릭 메서드에서 타입을 제한할 수 있음
      ex) `<T extends Comparable<T>` 처럼 상위 클래스나 인터페이스를 지정하여 특정 기능을 지원 하는 타입만 사용하도록 할 수 있음
- 와일드카드 사용
    - 와일드 카드를 사용하여 상한 제한 또는 하한 제한 설정 가능
- 타입 추론
    - 제네릭 메서드를 호출할 때 컴파일러가 타입을 추론함 → 호출 시 명시적으로 타입 지정 필요❌
- primitive 타입 사용 불가