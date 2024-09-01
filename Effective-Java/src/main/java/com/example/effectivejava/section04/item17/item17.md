<aside>
💡

불변 클래스?
→ 인스턴스의 내부 값을 수정할 수 없는 클래스
→ 불변 인스턴스에 간직된 정보는 고정되어 객체가 파괴되는 순간까지 절대 달라지지 않음

</aside>

### 불변 객체 장점

- 안전한 동기화 : 불변 객체는 상태가 변하지 않기에, 멀티스레드 환경에서 안전하게 사용가능
  → 여러 스레드에서 동시에 접근해도 레이스 컨디션(race condition)이 발생하지 않음

    <aside>
    💡

  race condition?

    - 두 개 이상의 프로세스 혹은 스레드가 공유 자원을 서로 사용하려고 경합하는 현상
    </aside>

- 단순성 : 불변 객체는 복잡한 상태 관리 로직이 필요하지 않기에 코드가 간결하고 이해하기 쉬움
- 오류 방지 : 불변 객체는 상태 변경을 허용하지 않기에 예기치 않은 수정으로 인한 오류를 방지 할 수 있음
- 캐싱 및 성능 최적화 : 동일한 불변 객체는 재사용이 가능하기때문에 캐싱이나 성능 최적화에 유리함

### 불변 객체 만드는 방법

- 객체의 상태를 변경하는 메서드를 제공하면 안됨(ex. `setter` )
- 클래스를 확장할 수 없도록 해야 함(클래스를 `final`로 선언)
- 모든 필드를 `final`로 선언하여 변경할 수 없도록 해야 함
- 모든 필드를 `private`로 선언하여 외부에서 접근할 수 없도록 해야함
- 가변 객체를 참조하는 필드는 외부로부터 방어적 복사 해야함

    <aside>
    💡

  방어적 복사?

    - 가변 객체를 외부에 반환하거나 외부로부터 받을 때, 원본 객체를 직접 사용하지 않고 해당 객체의 복사본을 만들어 사용함
      → 외부 코드가 원본 객체를 변경하는것을 방지
    </aside>


### 불변 객체 예시 코드

```java
public final class Point {
    private final int x;
    private final int y;

    // 생성자
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter 메서드
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // 두 점이 동일한지 확인하는 메서드 (불변성을 유지)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

```

## 📌 가변 객체를 참조하는 불변 객체

- 불변 객체가 가변 객체를 참조(필드)해야 하는 경우, 그 참조도 불변으로 유지되도록 방어적으로 복사해야 함

### 예시 코드 `Date`를 필드로 참조하는 경우

```java
import java.util.Date;

public final class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        // 방어적 복사
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.after(this.end)) {
            throw new IllegalArgumentException("start date must be before end date");
        }
    }

    public Date getStart() {
        return new Date(start.getTime()); // 방어적 복사
    }

    public Date getEnd() {
        return new Date(end.getTime()); // 방어적 복사
    }
}

```

- 원본 `Date`를 사용하지 않고 방어적 복사 `new Date(start.getTime())`을 이용해서 새로운 `Date` 객체 생성
  → `Date` 객체가 외부에서 변경되더라도 `Period` 객체 상태가 변경되지 않기 위함

## 📌 정리

- 불변 객체를 설계하여 객체 상태 변경을 방지하고, 안정성과 신뢰성을 높이는 것을 목표로 함
- 불변 객체를 만들기 위해 클래스와 필드를 `final`로 선언하고 상태 변경 메서드를 제공하지 않고, 가변 객체를 참조할 때는 방어적 복사를 수행해야 함
- 불변 객체는 멀티스레드 환경에서 안전하고 코드의 유지보수성과 오류 방지에 큰 도움이 됨
- 모든 클래스를 불변으로 만들 수 없기에 불변으로 만들지 못하는 클래스는 변경할 수 있는 부분을 최소한으로 줄여야 함