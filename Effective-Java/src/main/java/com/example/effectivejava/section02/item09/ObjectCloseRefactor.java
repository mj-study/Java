package com.example.effectivejava.section02.item09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectCloseRefactor {
	static String firstLineOfFile(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			return br.readLine();
		}
	}

	static void copy(String src, String dst) throws IOException {
		try (InputStream in = new FileInputStream(src);
			 OutputStream out = new FileOutputStream(dst)
		) {
			byte[] buf = new byte[8192];
			int n;
			while ((n = in.read(buf)) >= 0)
				out.write(buf, 0, n);
		}
	}
}
