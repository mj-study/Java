> 태그 클래스?
>
- 하나의 클래스 안에 여러 구현이 한 클래스에 혼합돼있는 구조
- 두 가지 이상의 의미를 표현할 수 있음

## 📌 태그 달린 클래스

```java
/**
 * 태그 달린 클래스로 계층구조보다 복잡하고, 파악하기 힘들다.
 */
public class Figure {
	enum Shape {RECTANGLE, CIRCLE}

	private final Shape shape;

	// 사각형일때 사용
	private double width;
	private double length;

	// 원일때 사용
	private double radius;

	// 사각형 전용 생성자
	public Figure(Shape shape, double width, double length) {
		this.shape = shape;
		this.width = width;
		this.length = length;
	}

	// 원 전용 생성자
	public Figure(Shape shape, double radius) {
		this.shape = shape;
		this.radius = radius;
	}

	// switch로 사각형, 원일때 면적 구함
	public double area() {
		switch (shape) {
			case CIRCLE -> {
				return Math.PI * (radius * radius);
			}
			case RECTANGLE -> {
				return length * width;
			}
		}
		throw new IllegalArgumentException("해당 모양이 없습니다.");
	}
}

```

- 위 클래스는 하나의 클래스 안에 `enum`, `area` 구하는 메서드, 사각형일때 사용하는 필드, 원일때 사용하는 필드 등 복잡하게 여러가지가 섞여있음
- 사각형인 인스턴스를 만들때 사용하지 않은 원 관련된 필드들..
- `Shape` 가 추가되는경우 `enum`과 case문이 추가되어야함 → 비효율적이고 오류를 내기 쉬움
- 태그 달린 클래스를 상속을 이용하여 계층 구조로 만들어 해결
  → 공통 분모를 가진것들을 `root` 클래스로 빼기

## 📌 계층 구조로 변경

```java
public class CorrectFigure {
	abstract class Figure{
		abstract double area();
	}
	
	class Circle extends Figure{
		private final double radius;

		public Circle(double radius) {
			this.radius = radius;
		}

		@Override
		double area() {
			return Math.PI * (radius * radius);
		}
	}
	
	class Rectangle extends Figure {
		private final double length;
		private final double width;

		public Rectangle(double length, double width) {
			this.length = length;
			this.width = width;
		}

		@Override
		double area() {
			return length * width;
		}
	}
	
	class Square extends Rectangle {
		public Square(double side) {
			super(side, side);
		}
	}
}

```

- 공통된 함수를 최상위 클래스에 추상 메서드로 도출하여 해당 메서드를 각 클래스에 맞게 구현
- 추상 메서드를 구현했는지 컴파일러가 확인하기에 실수로 미구현 할 일이 없음