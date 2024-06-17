- `equals`를 재정의한 클래스 모두에서 `hashCode`도 재정의 해야함
  → 그렇지 않으면 `hashCode` 일반 규약을 어기게 되어 해당 클래스의 인스턴스를 `HashMap`이나 `HashSet` 같은 컬렉션의 원소로 사용할 때 문제를 일으킬 것

> Object 명세에서 발췌한 규약
>
> - `equals` 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 `hashCode` 메서드는 몇 번을 호출해도 일관되게 같은 값을 반환해야 함
> - `equals(Object)`가 두 객체를 같다고 판단했다면, 두 객체의 `hashCode`는 똑같은 값을 반환해야함
> - `equals(Object)`가 두 객체를 다르다고 판단했더라도, 두 객체의 `hashCode`가 서로 다른 값을 반환할 필요는 없다.
    > 단 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.

- `equals`가 물리적으로 달라도 논리적으로 같을 수 있다.
  하지만 `Object`의 기본 `hashCode` 메서드는 이 둘이 전혀 다르다고 판단하여
  규약과 달리 서로 다른 값을 반환함
- 예시 코드

    ```java
    Map<PhoneNumber, String> m = new HashMap<>();
    m.put(new PhoneNumber(707, 867, 5309), "제니");
    m.get(new PhoneNumber(707, 867, 5309)); // => null
    
    /**
    get을 했을때 제니가 나올거같지만, PhoneNumber 클래스에서 따로 정의해둔 hashCode가 없기에 새로운 인스턴스로 인식하고 해당 인스턴스 hashCode로 값을 찾음
    */
    ```

    - `HashMap`은 해시코드가 다른 엔트리끼리는 동치성 비교를 시도조차 하지 않도록 최적화 되어있음

> 그렇다면 적절한 `hashCode` 메서드는?
>
- 좋은 해시 함수라면 서로 다른 인스턴스에 다른 해시코드를 반환함
  ⇒ 3번째 규약이 요구하는 속성
- 이상적인 해시 함수는 주어진 인스턴스들을 32비트 정수 범위에 균일하게 분배해야 함

### `HashCode` 작성법

- [기본 규약](https://www.notion.so/item11-equals-hashCode-8f3bda16bc5f44e78c7339654b4b0ea4?pvs=21)

> 작성 요령
>
- 중요한 (핵심) 필드를 기반으로 해시 코드를 계산 (클래스에 정의된 모든 필드가 아닌 핵심 필드)
- 기본 타입은 `int`로 직접 사용할 수 있음
- `float`는 `Float.floatToIntBits`를 사용하여 `int`로 변환
- `double`은 `Double.doubleToLongBits`를 사용하여 `long`으로 변환한 후 상위 비트와 하위 비트를 XOR 연산하여 `int` 로 변환
- 객체 참조 타입은 그 객체의 `hashCode` 메서드를 호출
- 배열은 배열의 각 요소에 대해 `hashCode`를 호출하고 이를 조합하여 해시 코드 생성

> 해시 코드 생성 방법
>
- 초기값 설정, 일반적으로 소수인 17 또는 31을 사용 (관례상 사용)
- 각 필드의 해시 코드를 곱하고 더하여 최종 해시 코드를 생성

> 예시 코드
>

```java
public class Person {
    private String name;
    private int age;
    private double salary;

    @Override
    public int hashCode() {
        int result = 17;  // 초기값으로 소수를 사용
        result = 31 * result + (name == null ? 0 : name.hashCode());  // String 필드
        result = 31 * result + Integer.hashCode(age);  // int 필드
        long temp = Double.doubleToLongBits(salary);  // double 필드
        result = 31 * result + (int) (temp ^ (temp >>> 32));  // double 필드를 int로 변환 후 해시 코드 계산
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && Double.compare(person.salary, salary) == 0 &&
                Objects.equals(name, person.name);
    }
}

```

- 파생 필드는 해시코드 계산에서 제외해도 됨
  ⇒ 다른 필드로부터 계산해낼 수 있는 필드는 모두 무시해도 됨
- `equals` 비교에 **사용되지 않은 필드는 반드시 제외**

### 해시 충돌이 적은 방법을 써야한다면?

- 구아바의 `com.google.common.hash.hashing` 참고
- `Objects` 클래스는 임의의 개수만큼 객체를 받아 해시 코드를 계산해주는 정적 메서드인 `hash` 제공
    - 일반적인 `hashCode` 구현과 비슷한 수준을 함수 단 한줄로 작성 가능
      하지만, 속도는 더 느림
    - 입력 인수를 담기 위한 배열이 만들어지고, 입력 중 기본 타입이 있다면 박싱과 언박싱을 거쳐야 하기 때문
    - 성능에 민감하지 않은 상황에서만 사용할 것

    ```java
    @Override
    public int hashCode(){
        return Objects.hash(lineNum, prefix, areaCode); 
    }
    ```


> 클래스가 불변이고 해시코드 계산하는 비용이 크다면?
>
- 매번 새로 계산하기보다는 캐싱하는 방식을 고려
- 해당 타입의 객체가 주로 해시의 키로 사용될 것 같다면 인스턴스가 만들어질때 해시코드를 계산해둬야 함

> 해시의 키로 사용되지 않는 경우라면?
>
- `hashCode`가 처음 불릴 때 계산하는 지연 초기화 전략?
- 필드를 지연 초기화하려면 그 클래스를 스레드 안전하게 만들도록 신경 써야 함
- 성능을 높인다고 해시코드를 계산할 때 핵심 필드를 생략해서는 안됨!
  ⇒ 해시 품질이 나빠져 해시테이블의 성능을 심각하게 떨어 뜨릴 수 있음
  ⇒ 어떤 필드는 특정 영역에 몰린 인스턴스들의 해시코드를 넓은 범위로 고르게 퍼트려주는 효과가 있을 수도 있음

## 📌 정리

- `equals`를 재정의할떄는 `hashCode`도 반드시 재정의 할 것
- 재정의한 `hashCode`는 `Object`의 API 문서에 기술된 일반 규약을 따라야 함
- 서로 다른 인스턴스라면 되도록 해시코드도 서로 다르게 구현해야 함
- `AutoValue` 프레임워크를 사용하면 간편하게 만들어줌