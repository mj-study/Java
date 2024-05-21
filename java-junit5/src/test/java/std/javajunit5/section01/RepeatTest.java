package std.javajunit5.section01;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import std.javajunit5.Study;

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
	// @EmptySource // 비어있는 문자를 인자로 추가해줌
	// @NullSource // null 인자 추가해줌
	@NullAndEmptySource
		// Empty + Null
	void parameterizedTest(String message) {
		System.out.println("message = " + message);
	}

	@DisplayName("csv 테스트")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	// @ValueSource(ints = {10,20,30,40})
	@CsvSource({"10, '낙성대 사는'", "20, 김모씨"})
		// void csvSourceTest (@ConvertWith(StudyConverter.class) Study study) { // -> 하나의 매개변수만 받을 때 사용
		// 1. void csvSourceTest (Integer limit, String name) {
		// 2. void csvSourceTest (ArgumentsAccessor accessor) {
	void csvSourceTest(@AggregateWith(StudyAggregator.class) Study study) {

		// System.out.println("study = " + study.getLimit());

		// 1. 직접 매개변수로 받기
		// Study study = new Study(limit, name);

		// 2. Argument 로 받기
		// Study study = new Study(accessor.getInteger(0), accessor.getString(1));

		// 3. 커스텀 Aggregator 로 받기
		System.out.println("study = " + study);
	}

	// Study => 대상 클래스가 public 일 때 사용 가능
	static class StudyAggregator implements ArgumentsAggregator {

		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws
			ArgumentsAggregationException {
			return new Study(accessor.getInteger(0), accessor.getString(1));
		}
	}

	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Study 타입만 가능함");
			return new Study(Integer.parseInt(source.toString()));
		}
	}

}