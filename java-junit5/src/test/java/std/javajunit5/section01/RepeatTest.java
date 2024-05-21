package std.javajunit5.section01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RepeatTest {

	@DisplayName("반복 테스트")
	@RepeatedTest(value = 5, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo info) {
		System.out.println(
			"횟수만큼 반복 " +
				info.getCurrentRepetition() +
				"\n" +
				info.getTotalRepetitions());
	}

	@DisplayName("파라미터 테스트")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(strings = {"수업", "언제까지", "들어야", "하나요"})
	void parameterizedTest(String message) {
		System.out.println("message = " + message);
	}
}
