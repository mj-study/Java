- 자바는 두 가지 객체 소멸자 제공
    - `finalizer`는 예측할 수 없고 상황에 따라 위험할 수 있어 일반적으로 불필요함
      ⇒ 오동작, 낮은 성능, 이식성 문제의 원인이 되기도 함
        - Java 9 에서는 deprecated로 지정 → `cleaner`를 대안으로 소개
    - `cleaner` 는 `finalizer` 보다 덜 위험하지만, 여전히 예측할 수 없고, 느리고, 일반적으로 불필요함
    - `finalizer`나 `cleaner` 로는 제때 실행되어야 하는 작업은 절대 할 수 없음
      → 실행되기까지 얼마나 걸릴지 알 수 없음

- 자바 언어 명세는 `finalizer` 나 `cleaner` 의 수행 시점뿐 아니라 수행 여부조차 보장하지 않음
  → 접근할 수 없는 일부 객체는 딸린 종료 작업을 전혀 수행하지 못한 채 프로그램이 중단될 수 있다는 얘기
  → 프로그램 생애주기와 상관없는, 상태를 영구적으로 수정하는 작업에서는 절대 `finalizer`나 `cleaner`에 의존해서는 안됨
  ex) DB 같은 공유 자원의 영구 lock 해제를 finalizer나 cleaner에 맡겨 놓으면 분산 시스템 전체가 서서히 멈출 것임

> 성능 문제
>
- `AutoCloseable` 객체를 생성하고 GC가 수거하기까지 12ns(`try-with-resource`) 걸린 반면`finalizer`와 `cleaner`를 사용하면 550ns가 걸림, 50배나 느림
- `finalizer`가 GC의 효율을 떨어뜨림

> `finalizer`나 `cleaner`를 대신해줄 묘안?
>
- `AutoCloseable` 을 구현하고, 클라이언트에서 인스턴스를 다 쓰면 `close` 메서드를 호출하면 된다.
  (일반적으로 예외가 발생해도 제대로 종료되도록 `try-with-resources` 사용)