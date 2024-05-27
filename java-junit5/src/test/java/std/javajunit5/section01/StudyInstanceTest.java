package std.javajunit5.section01;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.TestInstance;

import std.javajunit5.Study;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyInstanceTest {

	int value = 1;
	/*
	* @TestInstance(TestInstance.Lifecycle.PER_CLASS)
	* 테스트 인스턴스를 설정하면 @BeforeAll, @AfterAll 메서드가 static 일 필요 없음
	* */

	@BeforeAll
	void beforeAll() {
		System.out.println("before All");
	}

	@AfterAll
	void afterAll() {
		System.out.println("after All");
	}

	@FastTest
	@DisplayName("스터디 만들기 fast")
	void create_new_study () {
		System.out.println("현재 값 : "  + this);
		System.out.println(value++);
		Study actual = new Study(1);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}

	@SlowTest
	@DisplayName("스터디 만들기 slow")
	void create_new_study2() {
		System.out.println(this);
		System.out.println("create : " + value++);
	}
}
