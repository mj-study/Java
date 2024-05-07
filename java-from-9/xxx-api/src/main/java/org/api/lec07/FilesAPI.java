package org.api.lec07;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesAPI {
	public static void main(String[] args) throws Exception{
		var path = Paths.get(Paths.get(".").toAbsolutePath() + "/xxx-api/test.txt");
		String str = Files.readString(path);
		System.out.println("str = " + str);
	}
}
