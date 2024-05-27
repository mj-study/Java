package com.example.effectivejava.section02.item09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectClose {
	/*
	 * try-finally 이용해서 자원 닫기
	 * */
	static String firstLineOfFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			return br.readLine();
		} finally {
			br.close();
		}
	}

	/*
	* 자원이 2개 이상 닫아야 한다면 코드가 지저분해짐
	* */
	static void copy(String src, String dst) throws IOException {
		InputStream in = new FileInputStream(src);
		try {
    		OutputStream out = new FileOutputStream(dst);
    		try {
    			byte[] buf = new byte[8192];
    			int n;
    			while ((n = in.read(buf)) >= 0)
    				out.write(buf, 0, n);
    		} finally {
    			out.close();
    		}
    	} finally {
    		in.close();
    	}
	}

}
