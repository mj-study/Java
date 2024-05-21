package std.javajunit5;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

class TestTagging {

	@BeforeAll
	static void beforeAll() {
		System.out.println("before All");
	}

	@Test
	@DisplayName("스터디 만들기")
	@Tag("fast")
	void fastTest() {
		Study study = new Study(10);
		assertThat(study.getLimit()).isGreaterThan(0);
	}

	@Test
	@DisplayName("출력")
	@Tag("slow")
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
