package com.example.effectivejava.section02.item03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomeSerializableTest {

	public byte[] serialize(Object instance) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	public Object deserialize(byte[] serilaizedData){
		ByteArrayInputStream bis = new ByteArrayInputStream(serilaizedData);
		try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		HomeSingleTon instance = HomeSingleTon.getInstance();
		HomeSerializableTest test = new HomeSerializableTest();
		// 직렬화, 역직렬화 후 대상 비교
		byte[] serializedData = test.serialize(instance);

		HomeSingleTon home = (HomeSingleTon)test.deserialize(serializedData);
		/*
		* 실행결과는 둘 다 false
		* 직렬화와 역직렬화는 싱글턴 보장 x
		* */
		System.out.println("instance == result : " + (instance == home));
		System.out.println("instance.equals(result) : " + (instance.equals(home)));
	}
}
