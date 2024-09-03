<aside>
💡

상속(Inheritance) 대신 컴포지션(Composition)을 사용해야 하는 이유

- 상속은 강력한 기능이지만, 잘못 사용하면 유지보수와 확장성이 어려워지고, 오류를 발생 시킬 수 있는 취약한 코드를 초래할 수 있음
- 상속을 남용하지 않고, 가능한 경우 컴포지션과 위임(Delegation)을 사용하는 것이 바람직 함
</aside>

## 📌 상속 문제점

1. 캡슐화 위반
    - 상속은 부모 클래스와 자식 클래스 사이에 강한 결합을 만듦
    - 부모 클래스의 내부 구현이 변경되면 자식 클래스가 영향을 받을 수 있고, 자식 클래스가 부모 클래스의 동작을 의존할 경우 예기치 않은 오류를 발생할 수 있음
2. 유지보수 어려움
    - 부모 클래스가 변경될 때마다 모든 자식 클래스가 영향을 받을 수 있으므로, 코드를 유지보수하는것이 어려워 질 수 있음
3. 상속 오용
    - 상속은 `IS-A` 관계를 의미
      → 자식 클래스가 부모 클래스의 일종일 때
    - 상속이 적합하지 않은 경우에도 단순히 코드 재사용을 위해 상속을 사용하면, 설계의 유연성과 이해도가 떨어질 수 있음

## 📌 컴포지션 장점

1. 캡슐화 유지
    - 컴포지션은 기존 클래스를 새로운 클래스의 필드로 사용, 새로운 클래스의 메서드에서 이를 사용함
      → 기존 클래스 구현에 영향을 받지 않고, 변경에 유연하게 대응 가능
2. 유연한 재사용
    - 컴포지션을 사용하면 기존 클래스의 메서드를 직접 호출할 수 있고, 필요에 따라 새로운 클래스로 확장하거나 수정할 수 있음
3. 기능 확장과 수정
    - 컴포지션을 사용하면 객체의 기능을 자유롭게 수정하고 확장할 수 있고, 코드 재사용성이 향상됨

## 📌 상속 문제점 예시 코드

```java
import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
```

- 위 코드는 `HashSet`을 상속받아 `add` 메서드를 재정의함

### 문제점

- `addAll()` 는 내부적으로 `add()`를 호출함 따라서 `InstrumentedHashSet` 의 `addAll()` 가 호출될때 `addCount`가 원소 개수의 두 배로 증가하는 문제가 발생

```java
	@Test
	void addAll_test () throws Exception {
	    //given
		InstrumentedHashSet<String> set = new InstrumentedHashSet<>();

	    //when
		set.addAll(List.of("running", "swimming", "cycling"));

		//then
		// 기대하는 값은 3이지만 메서드 중복 호출로 인해 6
		Assertions.assertEquals(6, set.getAddCount());
	}
```

## 📌 컴포지션을 사용하여 해결한 코드

- 컴포지션을 사용하여 `HashSet`을 상속하지 않고 `HashSet`의 인스턴스를 내부적으로 사용

```java
import java.util.Collection;
import java.util.Set;

public class InstrumentedSet_V2<E> {
	private final Set<E> set;
	private int addCount = 0;

	public InstrumentedSet_V2(Set<E> set) {
		this.set = set;
	}

	public boolean add(E e) {
		addCount++;
		return set.add(e);
	}

	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return set.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
}

```

```java
	@Test
	void useComposition_addAll () throws Exception {
	    //given
		InstrumentedSet_V2<String> set = new InstrumentedSet_V2<>(new HashSet<>());

	    //when
		set.addAll(List.of("running", "swimming", "cycling"));

	    //then
	    // 예상 3, 실제3
		Assertions.assertEquals(3,set.getAddCount());
	}
```

### 컴포지션 코드 설명

1. 상속 대신 컴포지션 사용
    - `InstrumentedSet_V2` 는 `HashSet` 상속하는 대신 `Set<E>` 타입의 필드를 가지고 있으며, 생성자에서 `HashSet` 객체를 주입 받음
      → `HashSet`의 구현에 직접적으로 의존하지 않고, 유연하게 대체가 가능
2. 위임 사용
    - `add()`, `addAll()` 메서드에서는 내부의 `set` 객체 메서드를 호출하여 위임함
      → `HashSet`의 기존 메서드 호출 흐름을 유지하면서, 필요한 추가 기능을 구현할 수 있음
3. 유연성과 유지보수성 향상
    - `InstrumentedSet_V2` 내부 동작을 변경하고 싶다면 `Set`의 다른 구현체를 주입할 수 있음
      → ex) `TreeSet`으로 변경 가능
    - 클래스가 특정 구현체(`HashSet`)에 강하게 결합되지 않게 하여, 코드의 유연성과 유지 보수성을 크게 향상시킴

### 정리

- 상속보다는 컴포지션을 사용하면 코드를 더 유연하고, 재사용 가능하며, 유지보수성이 높도록 설계 하는 방법
- 상속을 남용하지 말고, 필요한 경우 컴포지션과 위임을 사용하여 클래스 간의 관계를 느슨하게 만들어야 함
- 이를 통해 코드의 의존성을 줄이고 코드의 재사용성과 확장성을 높일 수 있음