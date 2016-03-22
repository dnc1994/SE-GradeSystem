package gradeSystem;

/**
 * class: GradeAccumulator
 * 用於累加成績記錄
 */
public class GradeAccumulator {
	/**
	 * @uml.property  name="avgLab1"
	 */
	private double avgLab1;
	/**
	 * @uml.property  name="avgLab2"
	 */
	private double avgLab2;
	/**
	 * @uml.property  name="avgLab3"
	 */
	private double avgLab3;
	/**
	 * @uml.property  name="avgMidTerm"
	 */
	private double avgMidTerm;
	/**
	 * @uml.property  name="avgFinalExam"
	 */
	private double avgFinalExam;
	/**
	 * @uml.property  name="avgTotalGrade"
	 */
	private double avgTotalGrade;
	
	/** 
	 * Empty Constructor
	 */
	GradeAccumulator() {
		avgLab1 = 0.0;
		avgLab2 = 0.0;
		avgLab3 = 0.0;
		avgMidTerm = 0.0;
		avgFinalExam = 0.0;
		avgTotalGrade = 0.0;
	}
	
	/**
	 * method: addGrade
	 * 將單條成績記錄添加到累加器上
	 * @param Grade 要添加的成績記錄
	 */
	public void addGrade(Grade g) {
		avgLab1 += g.getLab1();
		avgLab2 += g.getLab2();
		avgLab3 += g.getLab3();
		avgMidTerm += g.getMidTerm();
		avgFinalExam += g.getFinalExam();
		avgTotalGrade += g.getTotalGrade();
	}
	
	/**
	 * method: averageGrade
	 * 將累加器的各項成績除以學生總數得到平均分
	 * @param int 學生總數
	 * @throws IllegalArgumentException
	 * pseudocode:
	 *     GradeAccumulator[:] /= [numStudents]
	 */
	public void averageGrade(int numStudents) {
		if (numStudents == 0) {
		    throw new IllegalArgumentException("Argument 'numStudents' cannot be 0.");
		}
		avgLab1 /= numStudents;
		avgLab2 /= numStudents;
		avgLab3 /= numStudents;
		avgMidTerm /= numStudents;
		avgFinalExam /= numStudents;
		avgTotalGrade /= numStudents;
	}

	/**
	 * method: getAvgLab1
	 * 獲取 Lab1 平均得分
	 * @return double 表示 Lab1 平均得分
	 * @uml.property  name="avgLab1"
	 */
	public double getAvgLab1() {
		return avgLab1;
	}
	
	/**
	 * method: getAvgLab2
	 * 獲取 Lab2 平均得分
	 * @return double 表示 Lab2 平均得分
	 * @uml.property  name="avgLab2"
	 */
	public double getAvgLab2() {
		return avgLab2;
	}
	
	/**
	 * method: getAvgLab3
	 * 獲取 Lab3 平均得分
	 * @return double 表示 Lab3 平均得分
	 * @uml.property  name="avgLab3"
	 */
	public double getAvgLab3() {
		return avgLab3;
	}
		
	/**
	 * method: getAvgMidTerm
	 * 獲取期中成績平均得分
	 * @return double 表示期中成績平均得分
	 * @uml.property  name="avgMidTerm"
	 */
	public double getAvgMidTerm() {
		return avgMidTerm;
	}
	
	/**
	 * method: getAvgFinalExam
	 * 獲取期末成績平均得分
	 * @return double 表示期末成績平均得分
	 * @uml.property  name="avgFinalExam"
	 */
	public double getAvgFinalExam() {
		return avgFinalExam;
	}
	
	/**
	 * method: getAvgTotalGrade
	 * 獲取總成績平均得分
	 * @return double 表示總成績平均得分
	 * @uml.property  name="avgTotalGrade"
	 */
	public double getAvgTotalGrade() {
		return avgTotalGrade;
	}
	
}
