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
		System.out.println(currentName + "�ĳɿ���");
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
		System.out.println(currentName + "���������" + rank + "λ��ȫ�๲" + num_students + "�ˣ���");
	}
	
	private void showAverageGrade() {
		System.out.println("���ƽ���֣�ȫ�๲" + gradeSystem.getNumStudents() + "�ˣ���");
		GradeAccumulator ga = gradeSystem.getAverageGrade();
		System.out.println("lab1:\t\t" + parseDoubleScore(ga.getAvgLab1()));
		System.out.println("lab2:\t\t" + parseDoubleScore(ga.getAvgLab2()));
		System.out.println("lab3:\t\t" + parseDoubleScore(ga.getAvgLab3()));
		System.out.println("midTerm:\t" + parseDoubleScore(ga.getAvgMidTerm()));
		System.out.println("finalExam:\t" + parseDoubleScore(ga.getAvgFinalExam()));
		System.out.println("totalGrade:\t" + parseDoubleScore(ga.getAvgTotalGrade()));
	}
	
	private void printOldWeights() {
		System.out.println("�f��֣�");
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
				System.out.println("ֻ��ݔ�딵�֣�");
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
		System.out.println("Ոݔ������֣�");
		int[] new_weights = newWeightsLoop();
		if (gradeSystem.checkAndSetWeights(new_weights)) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ����Ո�z����֔�ֵ�Ƿ������ؓ����Ӻ��100��");
		}
	}
	
	private void printCommandInfo() {
		System.out.println("Ոݔ��ָ�");
		System.out.println("1) G �@ʾ�ɿ�");
		System.out.println("2) R �@ʾ����");
		System.out.println("3) A �@ʾƽ��");
		System.out.println("4) W �������");
		System.out.println("5) E �x�_�x��");	
	}
	
	private boolean dispatchCommand(String command) {
		switch (command) {
			case "G": showGrade(); break;
			case "R": showRank(); break;
			case "A": showAverageGrade(); break;
			case "W": updateWeights(); break;
			case "E": return true;
			default: System.out.println("ָ����_��");
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
		System.out.println("Ոݔ��ID�Ե�䛣���ݔ��Q�˳���");
	}
	
	private void printLoginSuccessful() {
		System.out.println("�gӭ����" + currentName + "��");
	}
	
	private void printLoginFailed() {
		System.out.println("��ID�����ڡ�");
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
