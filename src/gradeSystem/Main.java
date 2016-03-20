package gradeSystem;

public class Main {
	public static void main(String[] args) {
		UI userInterface = new UI();
		System.out.println("您好，歡迎使用成績查詢系統。");
		userInterface.mainLoop();
		System.out.println("感謝使用成績查詢系統，再見！");
	}
}
