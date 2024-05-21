package std.javajunit5;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@Tag("fast")
	@DisplayName("StudyTest 클래스 fast")
	void fastTest () {
		System.out.println("fastTest 실행");
	}

	@Test
	@EnabledIfEnvironmentVariable(named = "LOCAL", matches = "local")
	void envVariable () {
		System.out.println(System.getenv("LOCAL"));
		System.out.println("특정 환경변수(name)이 matches(정규식 또는 값)과 일치할 때 사용");
	}

	@Test
	@EnabledIfSystemProperty(named = "java.version", matches = "1.8.xxx")
	void propertyVariable() {
		System.out.println("설정한 시스템 설정에 맞는 환경에서 실행");
	}

	@Test
	@DisplayName("복잡한 조건 검증")
	@EnabledOnOs(OS.MAC)
	void create_new_study2() {
		String testEnv = System.getenv("LOCAL");
		System.out.println("testEnv = " + testEnv);
		assumeTrue("LOCAL".equalsIgnoreCase(testEnv));

		// 1번째 매개변수의 조건이 만족하면, 2번째 매개변수 실행코드 실행
		assumingThat("LOCAL".equalsIgnoreCase(testEnv), () -> {
			Study actual = new Study(10);
			assertThat(actual.getLimit()).isGreaterThan(0);
			System.out.println("ㅎㅇ");
		});

		// Study actual = new Study(10);
		// assertThat(actual.getLimit()).isGreaterThan(0);
	}

	@Test
	@DisabledOnOs(OS.MAC)
	void create_new_study3() {
		System.out.println("mac 에서는 실행 ㄴ");
	}

	@Test
	@DisplayName("스터디 만들기")
	void create_new_study() {

		// 실행코드 무시하고, duration 끝나는 시간안에 무조건 테스트를 끝내겠다,
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			new Study(10);
			// Thread.sleep(1000);
		});

		// ## assertTimeout
		// Executable 실행코드가 앞 매개변수 Duration 으로 준 시간안에 실행되는지 체크
		// assertTimeout(Duration.ofMillis(100), () -> {
		// 	new Study(10);
		// 	Thread.sleep(1000);
		// });

		// ## assertThrows
		// IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
		// 	() -> new Study(-10));
		// assertEquals("limit 은 0보다 커야한다",exception.getMessage());

		//given
		// Study study = new Study(-10);
		//
		// // # assertAll 람다로 각 테스트를 묶으면 모든 테스트 실행, Executable
		// assertAll(
		// 	() -> assertNotNull(study),
		// 	// assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 함");
		// 	// # message 부분에서 supply 활용 가능 (람다) => Executable 타입 들어감 , 람다식으로 만들면 해당 테스트가 실패했을때만 실행함
		// 	() -> assertEquals(
		// 		StudyStatus.DRAFT, study.getStatus(),
		// 		() -> "스터디를 처음 만들면 상태값이" + StudyStatus.DRAFT + " 함"
		// 	),
		// 	() -> assertTrue(study.getLimit() > 0, "스터디 최대 참석인원은 0보다 커야함")
		// );

	}

}
