### 개요

`Comparable` 인터페이스는 Java에서 자연적인 순서를 정의하기 위해 사용됩니다. 이 인터페이스를 구현한 클래스는 기본적으로 `Collections.sort()`와 같은 메서드에서 사용할 수 있으며, 정렬된 컬렉션(예: `TreeSet`, `TreeMap`)에서도 사용됩니다.

`Comparable` 인터페이스를 올바르게 구현하면 객체 간의 순서를 비교할 수 있으며, 컬렉션 내에서 자연스러운 정렬을 지원합니다. 이 인터페이스는 단 하나의 메서드 `compareTo(T o)`를 정의합니다.

### `compareTo` 메서드

`compareTo` 메서드는 다음과 같이 동작해야 합니다:

- `a.compareTo(b)`가 음수이면, `a`가 `b`보다 작다고 판단됩니다.
- `a.compareTo(b)`가 0이면, `a`와 `b`가 같다고 판단됩니다.
- `a.compareTo(b)`가 양수이면, `a`가 `b`보다 크다고 판단됩니다.

### 기본 규칙

1. **반사성 (Reflexive)**: `x.compareTo(x)`는 항상 0을 반환해야 합니다.
2. **대칭성 (Symmetric)**: `x.compareTo(y)`가 양수라면 `y.compareTo(x)`는 음수를 반환해야 합니다.
3. **추이성 (Transitive)**: `x.compareTo(y)`가 양수이고, `y.compareTo(z)`도 양수라면 `x.compareTo(z)`도 양수를 반환해야 합니다.
4. **일관성 (Consistent)**: `x.compareTo(y)`가 0이면 `x.equals(y)`도 `true`여야 합니다.

### 예시 코드: `Person` 클래스의 `Comparable` 구현

아래는 `Person` 클래스를 정의하고, `Comparable` 인터페이스를 구현한 예시입니다.

```java
public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        // 1. 먼저 lastName으로 비교
        int lastNameComparison = lastName.compareTo(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        // 2. lastName이 같으면 firstName으로 비교
        int firstNameComparison = firstName.compareTo(other.firstName);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        // 3. firstName도 같으면 age로 비교
        return Integer.compare(age, other.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\\'' +
                ", lastName='" + lastName + '\\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", "Doe", 25));
        people.add(new Person("Jane", "Doe", 30));
        people.add(new Person("Alice", "Smith", 20));
        people.add(new Person("Bob", "Smith", 25));

        Collections.sort(people);

        for (Person person : people) {
            System.out.println(person);
        }
    }
}

```

### 예시 코드 설명

1. **필드**: `Person` 클래스는 `firstName`, `lastName`, `age` 필드를 가집니다.
2. **compareTo 메서드**:
    - `compareTo` 메서드에서는 우선적으로 `lastName`을 기준으로 비교합니다.
    - 만약 `lastName`이 같다면, `firstName`을 기준으로 비교합니다.
    - `firstName`도 같다면 마지막으로 `age`를 기준으로 비교합니다.
    - 이를 통해 `Person` 객체는 `lastName -> firstName -> age` 순서로 자연스럽게 정렬됩니다.
3. **Collections.sort**:
    - `Collections.sort(people)`를 호출하면 `Person` 객체들이 `compareTo` 메서드의 로직에 따라 정렬됩니다.
4. **toString 메서드**:
    - `toString` 메서드는 `Person` 객체의 문자열 표현을 제공합니다. 정렬된 결과를 출력할 때 사용됩니다.

### 정렬 결과

위의 코드에서 `Collections.sort(people)`을 호출하면 다음과 같은 순서로 출력됩니다:

```
Person{firstName='Jane', lastName='Doe', age=30}
Person{firstName='John', lastName='Doe', age=25}
Person{firstName='Alice', lastName='Smith', age=20}
Person{firstName='Bob', lastName='Smith', age=25}

```

- 성(`lastName`)을 기준으로 먼저 정렬되고, 이름(`firstName`)을 기준으로 그 다음으로 정렬되며, 나이(`age`)는 마지막으로 고려됩니다.

### 추가 고려 사항

- **null 안전성**: `compareTo` 메서드를 구현할 때 null 값에 대한 처리가 필요할 수 있습니다. 일반적으로 null은 가장 작은 값으로 취급됩니다.
- **equals와의 일관성**: `compareTo` 메서드가 `equals` 메서드와 일관성을 유지해야 합니다. 즉, `x.compareTo(y) == 0`이면 `x.equals(y)`도 `true`여야 합니다.

`Comparable` 인터페이스를 올바르게 구현하면, 객체의 자연스러운 순서를 쉽게 정의할 수 있으며, 이는 다양한 Java 컬렉션과 함께 사용될 수 있습니다.