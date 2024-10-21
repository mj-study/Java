### 제네릭 사용 이유?

- 제네릭 : 타입 파라미터를 사용하여 클래스나 메서드의 타입 안정성을 보장하는 기법
- 제네릭을 사용하지 않은 클래스 or 메서드는 타입을 명시하지 않기에 런타임 시 타입 에러 발생 가능성 ⭕
  제네릭 사용시 → 컴파일 타임에 타입 확인 가능, 오류를 더 빨리 발견함

### 제네릭 사용 전 예시 코드

```java
Object[] stack = new Object[10];
stack[0]="String"; // OK
Integer num = (Integer) stack[0]; // 런타임 에러 (ClassCastException)
```

- 제네릭을 사용하지 않으면 객체를 추가하거나 가져올 때 형변환이 필요함
  → 형변환은 런타임 에러를 발생 시킬 수 있는 원인이 됨 (`ClassCastException`)
- 제네릭 사용 → 컴파일 시점에 타입이 강제되므로 형변환 과정에서 오류를 방지할 수 있음

### 제네릭 사용 후 예시 코드

```java
class Stack<E> {

    private E[] elements;
    private int size = 0;

    public Stack() {
        elements = (E[]) new Object[10]; // 제네릭 배열 생성 불가로 인해 캐스팅
    }

    public void push(E e) {
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
}
```

### 제네릭 사용시 주의사항

- 제네릭 사용시 PECS(Producer Extends, Consumer Super) 원칙을 따름
    - `<? extends T>` : 데이터 제공자, 상위 타입을 제한하며 읽기 작업에 적합
    - `<? super T>` : 데이터 소비자, 하위 타입을 제한하며 쓰기 작업에 적합
- 제네릭 타입은 primitive 타입을 직접 사용❌
  → 래퍼 클래스 사용 ⭕
- 제네릭 타입은 배열로 생성할 수 없음 → Object 배열로 생성한 후 형변환