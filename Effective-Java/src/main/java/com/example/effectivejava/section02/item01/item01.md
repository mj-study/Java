# item01 - 생성자 대신 정적 팩토리 메서드 고려

태그: 정적팩토리메소드, 정적팩토리메소드 명명규칙

- 클라이언트가 클래스 인스턴스 얻는 전통적인 수단은 public 생성자
- 생성자와 별도로 정적 팩토리 메서드 제공 가능 (static factory method)
  ⇒ 해당 클래스의 인스턴스를 반환하는 단순한 정적 메서드

```java
public static Boolean valueOf(boolean b){
  return b ? Boolean.TRUE : Boolean.FALSE;
}
```

- 정적 팩토리 메서드는 디자인 패턴에서 말하는 팩토리 메서드와 다름 ⚠️ 혼동 주의

### ✅ 정적 팩토리 메서드가 생성자보다 좋은점

1. 이름을 가질 수 있음
    - 생성자는 넘기는 매개변수와 반환되는 객체만 보고 객체의 특성을 잘 이해하지 못할 수 있음
    - 정적 팩토리 메서드는 이름만 잘 지으면 반환될 객체 특성을 잘 알 수 있음
2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 됨
    - 플라이웨이트 패턴(Flyweight pattern)과 비슷한 기법
    - 인스턴스를 미리 만들어놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로 불필요한 객체 생성 피할 수 있음
    - 반복되는 요청에 같은 객체를 반환하는 식으로 정적 팩토리 방식의 클래스는 언제 어느 인스턴스를 살아 있게 할지 통제 가능
3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력
    - 반환할 객체 클래스를 자유롭게 선택할 수 있는 유연성 제공
        - API 작성시 이 유연성을 응용하면 구현 클래스를 공개하지 않고도 객체를 반환할 수 있어 API를 작게 유지할 수 있음
        - 인터페이스를 정적 팩토리 메서드 반환 타입으로 사용하는 인터페이스 기반 프레임워크를 만드는 핵심 기술이기도 함
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있음
    - 반환타입의 하위 타입이기만 하면 어떤 클래스를 반환해도 상관없음
5. 정적 팩토리 메서드를 작성하는 시점에 반환할 객체의 클래스가 존재하지 않아도 됨
    - 대표적인 서비스 제공자 프레임워크로 `JDBC`가 있음
      ⇒ 서비스 제공자 프레임워크에서 제공자는 서비스 구현체임

### ✅ 정적 팩토리 메서드 단점

1. 상속을 하려면 `public` 이나 `protected` 생성자가 필요함, 정적 팩토리 메서드만 제공하면 하위 클래스를 만들 수 없음
2. 정적 팩토리 메서드는 프로그래머가 찾기 어렵다
    - 그런가?. ,
    - 생성자처럼 API 설명에 명확히 드러나지 않으니 사용자는 정적 팩토리 메서드 방식 클래스를 인스턴스화할 방법을 알아내야 함

### ✅ 정적 팩토리 메서드 명명규칙
|메서드명|설명|
| --- | --- |
| from | 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드 ⇒ 해당 타입을 변환하는거니, 매개변수를 여러 개 받아서 반환하는것도 괜찮을듯?
ex) Date d = Date.from(instant); |
| of | 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드
ex) Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING); |
| valueOf | from 과 of의 더 자세한 버전
ex) BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);  |
| instance 또는 getInstance | (매개변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않음
ex) StackWalker luke = StackWalker.getInstance(options); |
| create 혹은 newInstance | instacne 혹은 getInstacne와 같지만, 매번 새로운 인스턴스를 생성해 반환을 보장
ex) Object newArray = Array.newInstance(classObject, arrayLen); |
| getType | getInstance 와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용. “Type”은 팩토리 메서드가 반환할 객체의 타입
ex) FileStore fs = Files.getFileStore(path) |
| newType | newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩토리 메서드를 정의할 때 사용. Type은 팩토리 메서드가  반환할 객체의 타입임
ex) BufferedReader br = Files.newBufferedReader(path) |
| type | getType 과 newType의 간결한 버전
ex) List<Compliant> litany = Collections.list(legacyLitancy); |

>📌 정리  
>- 정적 팩토리 메서드와 public 생성자는 각자 쓰임새가 있으니 상대적인 장단점 이해하고 쓰기
>- 그렇다고해도 정적 팩토리 사용하는게 유리한 경우가 더 많으니 무작정 `public` 생성사 제공하는 습관이 있었다면 고치기
