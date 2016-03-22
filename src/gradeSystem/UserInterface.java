package gradeSystem;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

/**
 * class: UserInterface
 * 實現與用戶的交互
 */
public class UserInterface {
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
	
	/** 
	 * Constructor
	 * @throws FileNotFoundException, UnsupportedEncodingException
	 * pseudocode:
	 *     gradeSystem <- new GradeSystem()
	 *     currentID <- ""
	 *     reader <- new Scanner()
	 */
	UserInterface(String gradeFile) throws FileNotFoundException, UnsupportedEncodingException {
		gradeSystem = new GradeSystem(gradeFile);
		currentID = "";
		reader = new Scanner(System.in);
	}
	
	/**
	 * method: parseIntScore
	 * 將分數轉化為顯示用的字符串，小于 60 的分數要加星號表示
	 * @param int 慾轉化的分數
	 * @return String 表示格式化后的分數字符串
	 * pseudocode:
	 *     s <- format(score)
	 *     if s < 60
	 *         then s += '*'
	 *     return s
	 */
	String parseIntScore(int score) {
		String s = String.format("%d", score);
		if (score < 60) s+= "*"; 
		return s;
	}
	
	/**
	 * method: parseDoubleScore
	 * 將分數轉化為顯示用的字符串，小于 60 的分數要加星號表示
	 * @param double 慾轉化的分數
	 * @return String 表示格式化后的分數字符串
	 * pseudocode:
	 *     s <- format(score)
	 *     if s < 60
	 *         then s += '*'
	 *     return s
	 */
	String parseDoubleScore(double score) {
		String s = String.format("%.1f", score);
		if (score < 60) s+= "*"; 
		return s;
	}
	
	/**
	 * method: showGrade
	 * 顯示當前用戶的成績 
	 */
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

	/**
	 * method: showRank
	 * 顯示當前用戶的排名
	 */	
	private void showRank() {
		int rank = gradeSystem.getRank(currentID);
		int num_students = gradeSystem.getNumStudents();
		System.out.println(currentName + "的排名為第" + rank + "位（全班共" + num_students + "人）。");
	}

	/**
	 * method: showAverageGrade
	 * 顯示各項成績的平均分
	 */	
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

	/**
	 * method: printOldWeights
	 * 顯示舊配分
	 */	
	private void printOldWeights() {
		System.out.println("舊配分：");
		int[] oldWeights = gradeSystem.getWeights();
		System.out.println("lab1:\t\t" + String.format("%d", oldWeights[0]));
		System.out.println("lab2:\t\t" + String.format("%d", oldWeights[1]));
		System.out.println("lab3:\t\t" + String.format("%d", oldWeights[2]));
		System.out.println("midTerm:\t" + String.format("%d", oldWeights[3]));
		System.out.println("finalExam:\t" + String.format("%d", oldWeights[4]));
	}

	/**
	 * method: promptNewWeight
	 * 獲取用戶輸入的單項配分	
	 * @param String 當前修改的成績項目
	 * @return int 表示該項的新配分
	 * pseudocode:
	 *     loop
	 *         prompt(instruction)
	 *         reader.scanInt()
	 *         handle Exceptions
	 */	
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
	
	/**
	 * method: newWeightsLoop
	 * 與用戶交互獲取新配分
	 * @return int 表示用戶輸入的一組新配分
	 */		
	private int[] newWeightsLoop() {
		int[] newWeights = new int[5];
		newWeights[0] = promptNewWeight("lab1:\t\t");
		newWeights[1] = promptNewWeight("lab2:\t\t");
		newWeights[2] = promptNewWeight("lab3:\t\t");
		newWeights[3] = promptNewWeight("midTerm:\t");
		newWeights[4] = promptNewWeight("finalExam:\t");
		return newWeights;
	}
	
	/**
	 * method: updateWeights
	 * 修改配分
	 * pseudocode:
	 *     newWeights <- User Input
	 *     gradeSystem.checkAndSetWeights(newWeights)
	 *     prompt(Result)
	 */	
	private void updateWeights() {
		printOldWeights();
		System.out.println("請輸入新配分：");
		int[] newWeights = newWeightsLoop();
		if (gradeSystem.checkAndSetWeights(newWeights)) {
			System.out.println("修改成功。");
		} else {
			System.out.println("修改失敗，請檢查配分數值是否均不為負且相加后得100。");
		}
	}
	
	/**
	 * method: printCommandInfo
	 * 提示用戶輸入指令
	 */		
	private void printCommandInfo() {
		System.out.println("請輸入指令：");
		System.out.println("1) G 顯示成績");
		System.out.println("2) R 顯示排名");
		System.out.println("3) A 顯示平均");
		System.out.println("4) W 更新配分");
		System.out.println("5) E 離開選單");	
	}

	/**
	 * method: dispatchCommand
	 * 根據指令確定下一步
	 * @param String 用戶輸入的指令
	 * @return boolean 表示是否跳出命令循環
	 */		
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
	
	/**
	 * method: commandLoop
	 * 接收指令的循環
	 * @return boolean 表示是否跳出命令循環
	 */		
	private boolean commandLoop() {
		while (true) {
			printCommandInfo();
			String input = reader.next().replace("\n", "").replace("\r", "");
			return dispatchCommand(input);
		}
	}

	/**
	 * method: printLoginInfo
	 * 提示用戶輸入 ID 進入系統或退出
	 */		
	private void printLoginInfo() {
		System.out.println("請輸入ID以登錄，或輸入Q退出。");
	}

	/**
	 * method: printLoginSuccessful
	 * 顯示歡迎信息
	 */		
	private void printLoginSuccessful() {
		System.out.println("歡迎您，" + currentName + "。");
	}

	/**
	 * method: printLoginFailed
	 * 提示輸入的 ID 不存在
	 */		
	private void printLoginFailed() {
		System.out.println("此ID不存在。");
	}

	/**
	 * method: setUser
	 * 設定當前用戶
     * @param String 慾設定的 ID
	*/
	private void setUser(String ID) {
		currentID = ID;
		currentName = gradeSystem.getNameByID(ID);
	}

	/**
	 * method: checkAndSetID
	 * 檢查 ID，若存在則設定為當前用戶
	 * pseudocode:
	 *     if exists(ID)
	 *         then setUser(ID)
	 *         print(SuccessMessage)
	 *     else
	 *         print(FailMessage)
	 */		
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
	
	/**
	 * method: loginLoop
	 * 登錄循環
	 * @return boolean 表示是否跳出登錄循環
	 */	
	private boolean loginLoop() {
		while (true) {
			printLoginInfo();
			String command = reader.next().replace("\n", "").replace("\r", "");
			if (command.equals("Q")) return true;
			else if (checkAndSetID(command)) return false;
		}
	}
	
	/**
	 * method: mainLoop
	 * 交互循環
	 * pseudocode:
	 *     loop
	 *         loginLoop()
	 *         loop
	 *             commandLoop()
	 */	
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
