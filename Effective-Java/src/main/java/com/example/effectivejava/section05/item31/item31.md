## 📌 PECS (Producer Extends, Consumer Super)

### Producer Extends

- 데이터를 제공(생산)하는 역할
- `? extends T` : T 타입이거나 T의 하위 타입만 허용
- 데이터를 읽을 수만 있고, 쓸 수는 없음

```java
// Producer 예제
public class PECSExample {

    // 동물 계층 구조
    class Animal {

    }

    class Dog extends Animal {

    }

    class Cat extends Animal {

    }

    public void producerExample() {
        // Producer - extends 사용
        List<? extends Animal> animals = new ArrayList<Dog>(); // OK

        // 읽기 가능
        Animal animal = animals.get(0);    // OK

        // 쓰기 불가능
        animals.add(new Dog());  // 컴파일 에러!
        animals.add(new Animal());  // 컴파일 에러!
    }

    // 실제 활용 예제 - 동물들의 목록을 출력하는 메서드
    public void printAnimals(List<? extends Animal> animals) {
        for (Animal a : animals) {
            System.out.println(a);
        }
    }
}
```

### Consumer Super

- 데이터를 소비(받아들이는) 역할
- `? super T` : T 타입이거나 T의 상위 타입만 허용
- 데이터를 쓸 수 있지만, 읽을 때는 Object 타입으로만 읽을 수 있음

```java
public class PECSExample {

    class Animal {

    }

    class Dog extends Animal {

    }

    public void consumerExample() {
        // Consumer - super 사용
        List<? super Dog> dogs = new ArrayList<Animal>(); // OK

        // 쓰기 가능
        dogs.add(new Dog());     // OK

        // 읽기는 Object 타입으로만 가능
        Object obj = dogs.get(0);  // OK
        Dog dog = dogs.get(0);     // 컴파일 에러!
    }

    // 실제 활용 예제 - 개들을 리스트에 추가하는 메서드
    public void addDogs(List<? super Dog> dogs) {
        dogs.add(new Dog());
        // dogs.add(다른 Dog 객체들...)
    }
}
```

---

### 핵심 원칙

1. 유연성을 극대화 하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입 사용하기
2. PECS 공식 기억
3. `Comparable`과 `Comparator`는 모두 소비자(consumer)임

```java
public class Stack<E> {

    private List<E> elements;
    private int size = 0;

    public Stack() {
        elements = new ArrayList<>();
    }

    public void push(E e) {
        elements.add(e);
        size++;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements.get(--size);
        elements.remove(size);
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 1. 생산자(producer) 매개변수에 와일드카드 타입 적용
    // 제네릭 메서드
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    // 2. 소비자(consumer) 매개변수에 와일드카드 타입 적용
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}

// 실제 사용 예시
public class StackExample {

    public static void main(String[] args) {
        // Number 스택 생성
        Stack<Number> numberStack = new Stack<>();

        // Integer는 Number의 하위 타입이므로 가능
        Iterable<Integer> integers = Arrays.asList(1, 2, 3);
        numberStack.pushAll(integers); // OK

        // Double도 Number의 하위 타입이므로 가능
        Collection<Double> doubles = Arrays.asList(1.0, 2.0, 3.0);
        numberStack.pushAll(doubles); // OK

        // Object는 Number의 상위 타입이므로 가능
        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects); // OK
    }
}

// 3. Comparable, Comparator 예시
public class MaxExample {

    // Comparable은 소비자이므로 super 사용
    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        if (list.isEmpty())
            throw new IllegalArgumentException("Empty list");

        T result = list.get(0);
        for (int i = 1; i < list.size(); i++)
            if (result.compareTo(list.get(i)) < 0)
                result = list.get(i);
        return result;
    }
}

// 4. 실제 활용 예시 - 타입 계층
class Animal implements Comparable<Animal> {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Animal o) {
        return this.name.compareTo(o.name);
    }
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }
}

// 사용 예시
public class ComparableExample {

    public void example() {
        List<Dog> dogs = Arrays.asList(
            new Dog("Buddy"),
            new Dog("Max"),
            new Dog("Charlie")
        );

        // Dog은 Animal의 Comparable을 상속받아 사용
        Dog maxDog = MaxExample.max(dogs); // 정상 작동
    }
}
```