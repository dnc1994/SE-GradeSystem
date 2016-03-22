package gradeSystem;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

public class UI {
	/**
	 * @uml.property  name="gradeSystem"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	GradeSystem gradeSystem;
	/**
	 * @uml.property  name="currentID"
	 */
	String currentID;
	/**
	 * @uml.property  name="currentName"
	 */
	String currentName;
	/**
	 * @uml.property  name="reader"
	 */
	Scanner reader;
	
	UI(String gradeFile) throws FileNotFoundException, UnsupportedEncodingException {
		gradeSystem = new GradeSystem(gradeFile);
		currentID = "";
		reader = new Scanner(System.in);
	}
	
	String parseIntScore(int score) {
		String s = String.format("%d", score);
		if (score < 60) s+= "*"; 
		return s;
	}
	
	String parseDoubleScore(double score) {
		String s = String.format("%.1f", score);
		if (score < 60) s+= "*"; 
		return s;
	}
	
	private void showGrade() {
		Grade g = gradeSystem.getGrade(currentID);
		System.out.println(currentName + "的成績：");
		System.out.println("lab1:\t\t" + parseIntScore(g.getLab1()));
		System.out.println("lab2:\t\t" + parseIntScore(g.getLab2()));
		System.out.println("lab3:\t\t" + parseIntScore(g.getLab3()));
		System.out.println("midTerm:\t" + parseIntScore(g.getMidTerm()));
		System.out.println("finalExam:\t" + parseIntScore(g.getFinalExam()));
		System.out.println("totalGrade:\t" + parseDoubleScore(g.getTotalGrade()));
	}
	
	private void showRank() {
		int rank = gradeSystem.getRank(currentID);
		int num_students = gradeSystem.getNumStudents();
		System.out.println(currentName + "的排名為第" + rank + "位（全班共" + num_students + "人）。");
	}
	
	private void showAverageGrade() {
		System.out.println("各項平均分（全班共" + gradeSystem.getNumStudents() + "人）：");
		GradeAccumulator ga = gradeSystem.getAverageGrade();
		System.out.println("lab1:\t\t" + parseDoubleScore(ga.getAvgLab1()));
		System.out.println("lab2:\t\t" + parseDoubleScore(ga.getAvgLab2()));
		System.out.println("lab3:\t\t" + parseDoubleScore(ga.getAvgLab3()));
		System.out.println("midTerm:\t" + parseDoubleScore(ga.getAvgMidTerm()));
		System.out.println("finalExam:\t" + parseDoubleScore(ga.getAvgFinalExam()));
		System.out.println("totalGrade:\t" + parseDoubleScore(ga.getAvgTotalGrade()));
	}
	
	private void printOldWeights() {
		System.out.println("舊配分：");
		int[] oldWeights = gradeSystem.getWeights();
		System.out.println("lab1:\t\t" + String.format("%d", oldWeights[0]));
		System.out.println("lab2:\t\t" + String.format("%d", oldWeights[1]));
		System.out.println("lab3:\t\t" + String.format("%d", oldWeights[2]));
		System.out.println("midTerm:\t" + String.format("%d", oldWeights[3]));
		System.out.println("finalExam:\t" + String.format("%d", oldWeights[4]));
	}
	
	private int promptNewWeight(String s) {
		while (true) {
			System.out.print(s);
			try {
				return reader.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("只可輸入數字！");
				reader.next();
			}
		}
	}
	
	private int[] newWeightsLoop() {
		int[] newWeights = new int[5];
		newWeights[0] = promptNewWeight("lab1:\t\t");
		newWeights[1] = promptNewWeight("lab2:\t\t");
		newWeights[2] = promptNewWeight("lab3:\t\t");
		newWeights[3] = promptNewWeight("midTerm:\t");
		newWeights[4] = promptNewWeight("finalExam:\t");
		return newWeights;
	}
	
	private void updateWeights() {
		printOldWeights();
		System.out.println("請輸入新配分：");
		int[] new_weights = newWeightsLoop();
		if (gradeSystem.checkAndSetWeights(new_weights)) {
			System.out.println("修改成功。");
		} else {
			System.out.println("修改失敗，請檢查配分數值是否均不為負且相加后得100。");
		}
	}
	
	private void printCommandInfo() {
		System.out.println("請輸入指令：");
		System.out.println("1) G 顯示成績");
		System.out.println("2) R 顯示排名");
		System.out.println("3) A 顯示平均");
		System.out.println("4) W 更新配分");
		System.out.println("5) E 離開選單");	
	}
	
	private boolean dispatchCommand(String command) {
		switch (command) {
			case "G": showGrade(); break;
			case "R": showRank(); break;
			case "A": showAverageGrade(); break;
			case "W": updateWeights(); break;
			case "E": return true;
			default: System.out.println("指令不正確。");
		}
		return false;
	}
	
	private boolean commandLoop() {
		while (true) {
			printCommandInfo();
			String input = reader.next().replace("\n", "").replace("\r", "");
			return dispatchCommand(input);
		}
	}
	
	private void printLoginInfo() {
		System.out.println("請輸入ID以登錄，或輸入Q退出。");
	}
	
	private void printLoginSuccessful() {
		System.out.println("歡迎您，" + currentName + "。");
	}
	
	private void printLoginFailed() {
		System.out.println("此ID不存在。");
	}
	
	private void setUser(String ID) {
		currentID = ID;
		currentName = gradeSystem.getNameByID(ID);
	}
	
	public boolean checkAndSetID(String ID) {
		if (gradeSystem.containsID(ID)) {
			setUser(ID);
			printLoginSuccessful();
			return true;
		} else {
			printLoginFailed();
			return false;
		}
	}
	
	private boolean loginLoop() {
		while (true) {
			printLoginInfo();
			String command = reader.next().replace("\n", "").replace("\r", "");
			if (command.equals("Q")) return true;
			else if (checkAndSetID(command)) return false;
		}
	}
	
	public void mainLoop() {
		while (true) {
			boolean quit = loginLoop();
			if (quit) break;
			while (true) {
				boolean exit = commandLoop();
				if (exit) break;
			}
		}
	}
	
}
