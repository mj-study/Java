package std.javajunit5.section01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서를 지정하기 위한 어노테이션
class OrderStudyTest {

	// Junit 제공
	@Order(1)
	@SlowTest
	@DisplayName("첫 번째 테스트")
	void order1() {
		System.out.println("1번째");
	}

	@Order(2)
	@FastTest
	@DisplayName("두 번째 테스트")
	void order2 () {
		System.out.println("2번째");
	}

	@Order(4)
	@Test
	@DisplayName("네 번째 테스트")
	void order4() {
		System.out.println("4번째");
	}

	@Order(3)
	@Test
	@DisplayName("세 번째 테스트")
	void order3() {
		System.out.println("3번째");
	}
}
