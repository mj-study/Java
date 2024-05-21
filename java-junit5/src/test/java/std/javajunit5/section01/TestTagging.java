package std.javajunit5.section01;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import std.javajunit5.Study;

class TestTagging {

	@BeforeAll
	static void beforeAll() {
		System.out.println("before All");
	}

	// 커스텀 태그
	@DisplayName("스터디 만들기")
	@FastTest
	// @Test
	// @Tag("fast")
	void fastTest() {
		Study study = new Study(10);
		assertThat(study.getLimit()).isGreaterThan(0);
	}

	@DisplayName("출력")
	@SlowTest
		// @Test
		// @Tag("slow")
	void slowTest() {
		Stream.iterate(0, i -> i < 10, i -> i + 1)
			.forEach(i -> {
				System.out.println("i = " + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			});
	}

}
