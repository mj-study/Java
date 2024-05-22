`Arrays` 나 `Collections` 처럼 정적 메서드를 모아놓을 수 있음

의도적으로 인스턴스화를 막기 위해 접근제한자를 `private`으로 생성자를 추가

```java
public class Test {
  // 명시적으로 인스턴스화를 막기 위해 작성, 예외를 던져도 괜찮음
  private Test() {...} 
}
```