package com.example.effectivejava.section02.item10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.effectivejava.section02.item10.sysmmetry.CaseInsensitiveStringV1;
import com.example.effectivejava.section02.item10.sysmmetry.CaseInsensitiveStringV2;

class CaseInsensitiveStringTest {


	@Test
	void 대칭성_위배 () {
		String word = "test";
		CaseInsensitiveStringV1 caseWord = new CaseInsensitiveStringV1("Test");

		boolean sToC = word.equalsIgnoreCase(caseWord.toString()); // 예상결과 -> false
		boolean cToS = caseWord.equals(word); // 예상결과 -> true
		System.out.printf("sToC : %b, cToS : %b\n", sToC, cToS);
		assertNotEquals(sToC, cToS);
	}

	@Test
	void list_비교 () {
		String word = "test";
		CaseInsensitiveStringV1 cis = new CaseInsensitiveStringV1("Test");
		List<CaseInsensitiveStringV1> list = new ArrayList<>();
		list.add(cis);

		boolean res = list.contains(word);
		System.out.printf("결과 : %s\n", res);
		assertFalse(res);
	}

	@Test
	void 대칭성_개선 () {
		String word = "test";
		CaseInsensitiveStringV2 caseWord = new CaseInsensitiveStringV2("Test");

		boolean sToC = word.equalsIgnoreCase(caseWord.toString()); // 예상결과 -> false
		boolean cToS = caseWord.equals(word); // 예상결과 -> true
		System.out.printf("sToC : %b, cToS : %b\n", sToC, cToS);
		assertEquals(sToC, cToS);
	}
}