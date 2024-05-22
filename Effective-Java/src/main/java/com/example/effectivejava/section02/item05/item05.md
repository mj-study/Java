- 많은 클래스는 하나 이상의 자원에 의존
- 사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않음
    - 이를 해결하기 위해 클래스가 여러 자원을 지원해주며, 클라이언트가 원하는 자원을 사용하기
    - 인스턴스를 생성할 때 생성자에 필요한 자원 넘겨주는 방식 패턴 이용

    ```java
    public class SpellChecker {
      private final Lexicon dictionary;
      
      public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
      }
      
      public boolean isValid(String word) {...}
      public List<String> suggestions(String typo) {...}
    }
    ```

- 의존 객체 주입은 생성자, 정적 팩토리, 빌더 모두에 똑같이 적용 가능
- 이 패턴의 쓸만한 변형으로 생성자에 팩토리를 넘겨주는 방식이 있음

>    💡 팩토리?  
    호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 개게
    즉, 팩토리 메서드 패턴을 구현한 것  
    ⇒ Java 8에서 `Supplier<T>` 인터페이스가 팩토리를 표현한 완벽한 예시



✅ 의존 객체 주입이 유연성과 테스트 용이성을 개선해주지만, 의존성이 수 천 개나 되는 큰 프로젝트에서는 헷갈림
⇒ Dagger, Guice, Spring 같은 의존 객체 주입 프레임워크를 사용하면 이런 이슈들을 해결 가능함