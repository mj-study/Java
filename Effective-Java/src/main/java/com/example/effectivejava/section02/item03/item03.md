>💡 싱글턴?  
    => 객체상 유일해야하는 시스템 컴포넌트


### 싱글턴 만드는 방식

- 공통 → 생성자는 private
1. `public static` 멤버 만들기

    ```java
    public class Elvis{
      public static final Elvis INSTANCE = new Elvis();
      private Elvis(){...};  
      
      public void leaveTheBuilding() {...}
    }
    ```

    - `private` 생성자는 `public static final` 필드인 INSTANCE를 초기화 할 때 딱 한 번 수행됨
    - 예외는 권한이 있는 클라이언트가 리플레션 API인 `AccessibleObject.setAccessible` 을 이용해 `private` 생성자를 호출할 수 있음
      ⇒ 이러한 공격을 방어하려면 생성자를 수정하여 두 번째 객체가 생성되려 할 때 예외 던지면 된다.

   > 장점
   >
    - 해당 클래스가 싱글턴임이 API에 명백히 드러남
      ⇒ `public static` 필드가 `final` 이니 절대로 다른 객체를 참조할 수 없음
    - 간결함
2. 정적 팩터리 메서드를 `public static`  멤버로 제공

    ```java
    public class Elvis {
      private static final Elvis INSTANCE = new Elvis();
      private Elvis() {...}
      public static Elvis getInstance() {return INSTANCE;}
      
      public void leaveTheBuilding() {...}
    } 
    ```

    - `getInstance`는 항상 같은 객체 참조를 반환하므로 제 2의 Elvis 인스턴스가 만들어지지 않음
      ⇒ 리플렉션을 통한 생성은 예외

   > 장점
   >
    - API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다는 점
    - 정적 팩토리를 제네릭 싱글턴 팩토리로 만들 수 있다는 점
    - 정적 팩토리의 메서드 참조를 공급자(supplier)로 사용할 수 있다는 점

- 둘 중 하나의 방식으로 만든 싱글턴 클래스를 직렬화 하려면 단순히 `Serializable`을 구현한다고 선언하는 것만으로는 부족
  ⇒ 모든 인스턴스 필드를 일시적(transient) 이라고 선언후 `readResolve` 메서드를 제공해야함
  ⇒ 그렇지 않으면 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 만들어짐

3. 원소가 하나인 열거 타입 선언

    ```java
    public enum Elvis {
      INSTANCE;
      public void leaveTheBuilding() {...}
    }
    ```

    - public 필드 방식과 비슷하지만, 더 간결하고 추가 노력 없이 직렬화 가능
    - 복잡한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 막아줌


### 직렬화 & 역직렬화

- 싱글턴 객체를 직렬화, 역직렬화시 같은 객체를 보장 못하므로 따로 `readRelove`를  사용하여 이를 방지한다

>`readResolve` : 역직렬화 과정에서 사용  
>`writeReplace` : 직렬화 과정에서 사용


- Java 14 부터는 직렬화에 관련된 필드 또는 메서드에 `@Serial` 어노테이션을 통해 직렬화 관련된 코드임을 알려준다. (소스레벨에서 검증)