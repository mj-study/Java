### 용어 정리

- 클래스와 인터페이스 선언에 타입 매개변수가 쓰이면
  → **`제네릭 클래스`** or **`제네릭 인터페이스`**라 함

### 제네릭 개요

- 각각의 제네릭 타입은 일련의 **매개변수화 타입**을 정의함
- 클래스 또는 인터페이스 이름이 나오고 이어서 꺾쇠 괄호 안에 실제 타입 매개변수들을 나열
  → ex) `List<String>` (원소타입이 `String` 인 리스트를 뜻하는 매개변수화 타입)
- 제네릭 타입을 하나 정의하면 그에 딸린 **raw type**도 함께 정의됨

    <aside>
    💡

  raw type?

  제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때

    </aside>

- 제네릭 타입을 raw type으로 사용할 경우 컴파일 단계에서 오류를 발견하기가 어렵고,
  런타임 단계에서 발견되어 디버깅 하기가 어려울 수 있음
    - 예를들어, raw type으로 `Stamp` 용 컬렉션에 `Coin`을 넣게되면 컴파일러는 경고를 하지만, 오류를 뱉지는 않음 ⇒ 결국, 런타임 단계에서 `ClassCastException`이 발생하게 됨
- `raw type` 사용하게 되면 제네릭이 주는 안전성과 표현력을 모두 잃음

  > 그렇다면 만든이유?
  >
    - 호환성 문제 때문에 만들어둠
      → 제네릭을 받아들이기까지 거의 10년이 걸림
      → 많은 코드들이 제네릭 없이 작성되었고 이와 호환하기 위해 로 타입 허용
      (하지만 현재는 시간이 많이 지났기때문에 대부분의 코드들은 제네릭 타입이 있을것이라 생각됨)

### 비한정적 와일드카드 타입

- 제네릭 와일드카드를 사용하며 어떤 타입이든 담을 수 있는 제네릭 타입을 의미
  → ex) `Set<?>`
    - 하지만, 타입 안정성을 위해 특정한 타입을 삽입할 수 없음
      → `null`을 제외한 어떤 객체도 추가할 수 없음

    <aside>
    💡

  와일드카드?

  어떤 타입이 들어올 수 있지만, 그 타입이 무엇인지는 알 수 없기 때문에 안전한 삽입을 허용하지 않음

    </aside>

    - 타입을 알 수 없으나, 모든 요소는 `Object`로 간주됨
    - 타입 안정성이 높아 `ClassCastException`이 발생하지 않음
- 다양한 타입을 처리해야하지만, 추가 연산이 필요 없는 경우에 사용됨
  → 컬렉션을 읽기 전용으로 사용하거나, 타입에 상관없이 요소를 순회할 때 유용함