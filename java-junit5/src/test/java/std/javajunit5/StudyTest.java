package std.javajunit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {


	@Test
	@DisplayName("스터디 만들기")
	void create_new_study() {

		// 실행코드 무시하고, duration 끝나는 시간안에 무조건 테스트를 끝내겠다,
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			new Study(10);
			Thread.sleep(1000);
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
