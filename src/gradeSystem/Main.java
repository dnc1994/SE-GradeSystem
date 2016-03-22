package gradeSystem;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		UI userInterface = new UI("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput_ANSI.txt");
		System.out.println("您好，歡迎使用成績查詢系統。");
		userInterface.mainLoop();
		System.out.println("感謝使用成績查詢系統，再見！");
	}
}
