package gradeSystem;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		UI userInterface = new UI("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput_ANSI.txt");
		System.out.println("���ã��gӭʹ�óɿ���ԃϵ�y��");
		userInterface.mainLoop();
		System.out.println("���xʹ�óɿ���ԃϵ�y����Ҋ��");
	}
}
