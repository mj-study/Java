- Java에서는 GC를 통해 사용하지 않은 객체를 자동으로 정리해주지만 개발자가 직접 만든 객체에 대해서는 개발자가 따로 그 과정을 작성해주어야 함
- 아래는 Stack 코드를 간단하게 구현한것인데, 메모리 누수 하는 부분이 있음

```java
public class StackMemoryLeak {
	private Object[] elemetns;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public StackMemoryLeak() {
		elemetns = new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(Object o) {
		ensureCapacity();
		elemetns[size++] = o;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		/*
		* 메모리 누수 지점
		* 해당 객체를 꺼낸 뒤 남아 있는 원소는 자동으로 GC 되지 않음
		* 별도로 처리해주어야함
		* */
		return elemetns[--size];
	}

	private void ensureCapacity() {
		if (elemetns.length == size) {
			elemetns = Arrays.copyOf(elemetns, elemetns.length * 2 + 1);
		}
	}
}
```

- 위와 같이 작성하면 `pop` 부분에서 메모리 누수가 일어나게 된다.
  해당 부분을 객체 참조를 하기 위해 `null` 추가

```java
	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		/*
		* 메모리 누수 지점
		* 해당 객체를 꺼낸 뒤 남아 있는 원소는 자동으로 GC 되지 않음
		* 별도로 처리해주어야함
		* */
		// return elemetns[--size];
		
		/*
		* 메모리 누수 개선
		* 꺼낸 뒤 꺼낸 원소 index에 null로 객체 참조를 해줌
		* */
		Object result = elemetns[--size];
		elemetns[size] = null;
		return result;
	}
```

- `null` 처리한 객체를 참조하는일이 발생하면 `NPE` 가 발생하기때문에 종료시킨다는 이점도 있음

---

- 객체 참조를 `null` 처리하는 일은 예외적인 경우여야 함
  ⇒ 다 쓴 참조를 해제하는 가장 좋은 방법은 참조를 담은 변수를 유효 범위밖으로 밀어내는 것

> `null` 처리는 언제 해야할까?
>
- `Stack` 클래스가 메모리 누수에 취약한 이유는 자신이 메모리를 관리하기 때문
    - `Stack`은 객체 자체가 아니라 객체 참조를 담는 배열
    - `elements` 배열로 저장소 풀을 만들어 원소들을 관리
    - ⚠️ GC는 안쓰는 객체를 알 수 가없음
      ⇒ 비활성 영역에서 참조하는 객체도 똑같이 유효한 객체
      ⇒ 이렇다는것은 프로그래머만 아는 사실이기에 GC가 알 방법이 없음
      ⇒ 고로, 비활성 영역이 되는 순간 `null` 처리를 하여 더 이상 사용하지 않는다는것을 GC에게 알림

> 캐시 메모리 역시 누수를 일으키는 주범
>
- 객체 참조를 캐시에 넣고 이 사실을 잊은 채 해당 객체를 다 쓴 뒤로도 한참동안 놔두는 일이 많음
  ⇒ 이를 위한 해법은 여러가지가 있음
- 해결

    > 💡 Weak Reference  
      - Java에서는 세 가지 주요 유형의 참조 방식이 존재  
      1. 강한 참조(Strong Reference)  
       - `Integer prime = 1;`와 같은 가장 일반적인 참조 유형    
       → prime 변수는 값이 1인 Integer 객체애 대한 강함 참조를 가짐   
       → 이 객체를 가리키는 강한 참조가 있는 객체는 GC 대상이 되지 않음  
      <br>
       2. 부드러운 참조(Soft Reference)  
       - `SoftReference<Integer> soft = new SoftReference<Integer>(prime);` 같이 `SoftReferece` 클래스를 이용하여 생성이 가능함  
      → `prime == null` 상태가 되어 더 이상 원본(강한 참조)은 없고 대상을 참조하는 객체가 `SoftReference`만 존재할 경우 GC 대상으로 들어가도록 JVM 작동  
      → 약한 참조와 차이는 **메모리가 부족하지 않으면 굳이 GC하지 않음**  
      때문에 조금은 엄격하지 않은 캐시 라이브러리들에서 널리 사용되는것으로 알려짐  
      <br>
      3. 약한 참조(Weak Reference)  
      - `WeakReference<Inter> weak = new WeakReference<Integer>(prime);` 같이 WeakReference 클래스를 이용하여 생성 가능  
      → `prime == null` 되면 (해당 객체를 가리키는 참조가 약한참조일뿐인경우) GC 대상이 됨  
      → `SoftReference`와 차이점은 메모리가 부족하지 않더라도 GC 대상이 됨
  
1. 엔트리가 살아 있는 캐시가 필요한 상황이라면 `WeakHashMap`을 이용할 것
> 💡 일반적인 HashMap의 경우 key와 value가 put 되면 사용여부와 관계없이 해당 내용은 삭제되지 않음  
    → Map 안의 Element들이 일부는 사용되고 안될수도 있지만, 그 구현을 개발자에게 달려있음  
    → 예를 들어 key에 해당하는 객체가 더 이상 존재하지 않게되는 경우 
    <br>    
   어떤 객체가 null이 되어 버리면 해당 객체를 key로 하는 HashMap의 Element 더 이상 꺼낼 일이 없는 경우  
   <br>
   WeakHashMap은 WeakReference 특성을 이용하여 HashMap의 Element를 자동으로 제거, GC 해버린다.  
   ⇒ key에 해당하는 객체가 더 이상 사용되지 않는다고 판단되면 제거한다는 의미
   <br>
    - `WeakHashMap`은 주로 `equlas` 메서드가 == 연산자를 사용하는 key를 사용할 경우 유용함  
      ⇒ key가 버려지면 동일한 key는 생성되지 않으므로, 이후에 `WeakHashMap`에서 해당키의 조회를 수행하는 것은 불가능하기 때문  
    - `HashMap`과 동일하게 Thread-safe 하지 않으며 필요한 경우 `Collections.synchronizedMap` 이용 가능
   2. 메모리 누수 → 리스너 혹은 콜백
       - 클라이언트가 콜백을 등록만하고 명확히 해지 않음
         ⇒ 따로 조치하지 않으면 콜백은 계속 쌓여감
       - 이럴 때 콜백을 약한 참조에 저장하면 GC가 즉시 수거해감
         ex) `WeakHashMap`에 키로 저장