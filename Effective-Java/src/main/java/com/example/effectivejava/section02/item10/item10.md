### 아래와 같은 상황에서는 `equals` 재정의 하지 않는게 좋음

1. 각 인스턴스가 본질적으로 고유한 경우
    - 값을 표현하는게 아닌 동작으로 개체를 표현하는 경우 해당
    - `Thread`가 좋은 예

        ```java
        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
        
            if (obj instanceof WeakClassKey) {
                Class<?> referent = get();
                return (referent != null) &&
                        (((WeakClassKey) obj).refersTo(referent));
            } else {
                return false;
            }
        }
        ```

2. 인스턴스의 논리적 동치성을 검사할 일이 없는 경우
    - 예를 들어, `Pattern` 클래스는 `equals`를 재정의 해서 두 인스턴스가 같은 정규 표현식을 나타내는지를 검사하는(논리적 동치성 검사) 방법이 있을 수 있지만 개발자가 정규 표현식이 동일한지 판단하는거라면 `Object` 의 `equals`만으로 충분하다.
3. 상위 클래스에서 재정의한 `equals`가 하위 클래스에도 맞는 경우
    - 예를 들어, 대부분의 `Set` 구현체는 `AbstractSet` 이 구현한 `equals` 상속받아 사용
      `List` → `AbstractList`, `Map` → `AbstractMap`
    - 이런 경우들은 굳이 재정의할 필요 없음
4. 클래스가 `private`이거나 `package-private`이고 `equals` 메서드를 호출할 일이 없는 경우
    - 혹시나 다른 곳에서 호출되는 경우를 막으려면 아래와 같이 설정하자

    ```java
    @Override 
    public boolean equals(Objet o) {
      throw new AssertionError();
    }
    ```


>  그렇다면 `equals`가 재정의 되어야할때는?  
> - 객체 식별성(물리적으로 x) 아니라 논리적 동치성을 확인해야할 때, 상위 클래스의 `equals`가 논리적 동치성을 비교하도록 재정의되지 않았을 때  
> - 주로 값 클래스들이 여기에 해당됨 ⇒ Integer, String 과 같은 클래스  
> - 값 클래스라 해도 싱글턴 패턴처럼 값이 같은 인스턴스가 둘 이상 만들어지지 않는 클래스는 재정의 할 필요 x , `enum` 도 해당

---

## 📌 `equals` 재정의시 일반 규약

- 아래는 `Object` 명세에 적힌 규약

| 반사성(reflexivity) | null이 아닌 모든 참조 값 x에 대해, x.equals(x) 는 true |
| --- | --- |
| 대칭성(symmetry) | null이 아닌 모든 참조 값 x, y에 대해 x.equals(y)가 true 면 y.equals(x) 도 true |
| 추이성(transitivity) | null이 아닌 모든 참조 값 x,y,z에 대해 x.equals(y) true이고 y.equals(z) true이면 x.equals(z)도 true |
| 일관성(consistency) | null이 아닌 모든 참조 값 x,y에 대해 x.equals(y)를 반복해서 항상 true를 반환하거나 false를 반환 |
| null-아님 | null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 false |

### 사용할만한 equals가 되려면?

- 모든 원소가 같은 동치류에 속한 어떤 원소와도 서로 교환할 수 있어야 함
- **반사성**은 → 자기 객체와 같아야함
- **대칭성**은 → 두 객체는 서로에 대한 동치 여부를 똑같이 답해야함

> 대칭성 위배되는경우와 개선 사항
>

```java
/**
 * 대칭에 대한 동치를 확인하기 위한 클래스
 */
public class CaseInsensitiveString {
	private final String s;

	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}

	// 대칭성 위배되는 경우
	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveString) {
			return s.equalsIgnoreCase(
				((CaseInsensitiveString)o).s
			);
		}
		
		// 이 부분에서 다른 String.equals(o) 할 경우 다른 경우가 나올 수 있음
		if (o instanceof String) {
			return s.equalsIgnoreCase((String) o);
		}
		return false;	
	}
}
```

```java
	@Test
	void 대칭성_위배 () {
		String word = "test";
		CaseInsensitiveString caseWord = new CaseInsensitiveString("Test");

		boolean sToC = word.equalsIgnoreCase(caseWord.toString()); // 예상결과 -> false
		boolean cToS = caseWord.equals(word); // 예상결과 -> true
		System.out.printf("sToC : %b, cToS : %b\n", sToC, cToS);
		assertNotEquals(sToC, cToS);
	}
```

- 테스트 결과 서로 다른 값이 나온다.
- `CaseInsensitiveString` equals는 일반 String을 알지만, `String` 의 equals 는 `CaseInsensitiveString` 존재를 모름

```java
	@Test
	void list_비교 () {
		String word = "test";
		CaseInsensitiveString cis = new CaseInsensitiveString(word);
		List<CaseInsensitiveString> list = new ArrayList<>();
		list.add(cis);

		boolean res = list.contains(word);
		System.out.printf("결과 : %s\n", res);
		assertFalse(res);
	}
```

- collection 객체도 마찬가지로 equals의 결과는 false

```java
	@Override
	public boolean equals(Object o) {
		return o instanceof CaseInsensitiveStringV2 &&
			((CaseInsensitiveStringV2)o).s.equalsIgnoreCase(s);
	}
```

- 같은 클래스 타입일때만 비교하게 변경하면 대칭성 위배는 해결



> 추이성 위배되는 경우?
구체 클래스를 확장해 새로운 값을 추가하면서 `equals` 규약을 만족시킬 방법은 존재하지 않음
>
- 구체 클래스의 하위 클래스에서 값을 추가할 방법은 없지만, 우회해서 사용
    - 상속 대신 **컴포지션 사용**
    - `Point`를 상속하는 대신 `Point` 를 `ColorPoint`의 private 필드로 두고 일반 `Point`를 반환하는 view 메서드를 public 추가


> 일관성은 클래스가 불변이든 가변이든 `equals` 판단에 신뢰할 수 없는 자원이 끼어들게 해서는 안됨
>
- 예를 들어 `URL` 클래스의 equals는 주어진 URL과 매핑된 호스트의 IP 주소를 이용해 비교함
  ⇒ 호스트 이름을 IP 주소로 변경하면 네트워크를 통해야 하는데, 항상 결과가 같다고 보장할 수 없음

> null-아님
>
- 모든 객체가 `null`과 같지 않아야 함
- `o.equals(null)` 이 true를 반환x하게 해서는 안됨

```java
// 명시적 null 검사 할 필요 없음
@Override
public boolean equals(Object o) {
  if (o == null) {
    return false;
  } 
}

// 묵시적 null 검사
@Override
public boolean equals(Object o) {
  if (!(o instanceof MyType) {
    ...
  } 
}
```

---

### 정리

1. `==` 연산자를 사용해 입력이 자기 자신의 참조인지 확인
    - 자기 자신이면 true 반환 ⇒ 단순한 성능 최적화용
    - 비교 작업이 복잡한 상황일 때 값어치를 할 것임
2. `instanceof` 연산자로 입력디 올바른 타입인지 확인
3. 입력을 올바른 타입으로 형변환함
    - Java16 에 추가된 `instanceof pattern` 사용하면 편리
      ⇒ ex) `if ( !(o instanceof Mytype type){}`
4. 입력 객체와 자기 자신의 대응되는 핵심 필드들이 모두 일치하는지 하나씩 검사
    - `float` 과 `double`필드는 각각 정적 메서드인 `Float.compare(x1,x2)` `Double.compare(x1,x2)` 이용
      ⇒ 부동소수를 다뤄야 하기때문에 위의 메서드 사용
      ⇒ `equals` 사용하게 된다면 오토박싱을 수반할 수 있으니 성능상 좋지 않음

- 배열의 모든 원소가 핵심 필드라면 `Arrays.equals` 메서드들 중 하나를 사용 권장
- null도 정상 값으로 취급하는 참조 타입 필드라면 NPE 예방을 위해 `Objects.equals(o1,o2)` 비교

> 복잡한 필드를 가진 클래스를 비교할때
>
- 필드의 표준형(canonical form)을 저장해둔 후 표준형끼리 비교하면 훨씬 경제적
  ⇒ 불변 클래스에 제격
- 동기화용 lock 필드 같이 객체의 논리적 상태와 관련없는 필드는 비교하면 안됨!
- 핵심 필드로부터 계산해낼 수 있는 파생 필드를 굳이 비교할 필요는 없지만, 파생 필드를 비교하는게 더 빠를때도 있다
    - 자신의 영역을 캐시에 저장할 경우 캐시해둔 영역만 비교하면 결과를 곧 바로 볼 수 있음

> ⚠️ equals 구현을 다했다면 3가지 점검하기
>
1. 대칭적인가?
2. 추이성이 있는가?
3. 일관적인가?

### 주의사항

- `equals` 재정의할 땐 `hashCode`도 반드시 재정의
- 너무 복합하게 해결하려고 x
- `Object` 외의 타입을 매개변수로 받는 `equals` 메서드는 선언x
    - 오버로딩 주의! 아래는 재정의(오버라이딩)한게 아니라 다중 정의(오버로딩) 한것
      ⇒ ex) `public boolean equals(MyClass o) {}`
    - `@Override` 어노테이션을 사용하면 컴파일 단계에서  미리 예방 가능
- 꼭 필요한게 아니면 `equals` 재정의하지 말것

### 꿀팁?

- 구글이 만든 `AutoValue` 프레임워크
- `Lombok` 과 차이는?