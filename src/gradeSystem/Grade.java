package gradeSystem;

/**
 * class: Grade
 * 實現了單位學生的成績記錄
 */
public class Grade {
	/**
	 * @uml.property  name="iD"
	 */
	private String ID;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="lab1"
	 */
	private int lab1;
	/**
	 * @uml.property  name="lab2"
	 */
	private int lab2;
	/**
	 * @uml.property  name="lab3"
	 */
	private int lab3;
	/**
	 * @uml.property  name="midTerm"
	 */
	private int midTerm;
	/**
	 * @uml.property  name="finalExam"
	 */
	private int finalExam;
	/**
	 * @uml.property  name="totalGrade"
	 */
	private double totalGrade;
	
	/** 
	 * Empty Constructor
	 */
	Grade() {
		ID = "";
		name = "";
		lab1 = 0;
		lab2 = 0;
		lab3 = 0;
		midTerm = 0;
		finalExam = 0;
		totalGrade = 0.0;
	}
	
	/** 
	 * Constructor
	 */
	Grade(String _ID, String _name, int _lab1, int _lab2, int _lab3, int _midTerm, int _finalExam) {
		ID = _ID;
		name = _name;
		lab1 = _lab1;
		lab2 = _lab2;
		lab3 = _lab3;
		midTerm = _midTerm;
		finalExam = _finalExam;
		totalGrade = 0.0;
	}
	
	/**
	 * method: equals
	 * 重載 equals 用來比較兩個 instance 的所有屬性是否都相同，在 unit test 中需要用到
	 * @param Object o 另一個 instance
	 * @return boolean 表示兩個 instance 的所有屬性是否都相同
	 * pseudocode:
	 *     if o is not Grade then return false
	 *     if each property of o is the same as this
	 *         then return true
	 *     else
	 *         return false
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Grade)) return false;
		if (!ID.equals(((Grade)o).getID())) return false;
		if (!name.equals(((Grade)o).getName())) return false;
		if (lab1 != ((Grade)o).getLab1()) return false;
		if (lab2 != ((Grade)o).getLab2()) return false;
		if (lab3 != ((Grade)o).getLab3()) return false;
		if (midTerm != ((Grade)o).getMidTerm()) return false;
		if (finalExam != ((Grade)o).getFinalExam()) return false;
		return true;
	}

	/**
	 * method: getID
	 * 獲取總分
	 * @return String 表示學號
	 * @uml.property  name="iD"
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * method: getTotalGrade
	 * 獲取總分
	 * @return double 表示姓名
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * method: getLab1
	 * 獲取 Lab1 得分
	 * @return int 表示 Lab1 得分
	 * @uml.property  name="lab1"
	 */
	public int getLab1() {
		return lab1;
	}
	
	/**
	 * method: getLab2
	 * 獲取 Lab2 得分
	 * @return int 表示 Lab2 得分
	 * @uml.property  name="lab2"
	 */
	public int getLab2() {
		return lab2;
	}
	
	/**
	 * method: getLab3
	 * 獲取 Lab3 得分
	 * @return int 表示 Lab3 得分
	 * @uml.property  name="lab3"
	 */
	public int getLab3() {
		return lab3;
	}
		
	/**
	 * method: getMidTerm
	 * 獲取期中得分
	 * @return int 表示期中得分
	 * @uml.property  name="midTerm"
	 */
	public int getMidTerm() {
		return midTerm;
	}
	
	/**
	 * method: getFinalExam
	 * 獲取期末得分
	 * @return int 表示期末得分
	 * @uml.property  name="finalExam"
	 */
	public int getFinalExam() {
		return finalExam;
	}
	
	/**
	 * method: getTotalGrade
	 * 獲取總分
	 * @return double 表示總分
	 * @uml.property  name="totalGrade"
	 */
	public double getTotalGrade() {
		return totalGrade;
	}
	
	/**
	 * method: calcuateTotalGrade
	 * 根據給定的配分計算總分
	 * @param int[] 配分，以百分比的 int 傳入
	 * pseudocode:
	 *     totalGrade <- [lab1, lab2, lab3, midTerm, finalExam] * weights[:] / [100.0]
	 */
	public void calculateTotalGrade(int[] weights) {
		totalGrade = (weights[0] * lab1 + weights[1] * lab2 + weights[2] * lab3 + weights[3] * midTerm + weights[4] * finalExam) / 100.0;
	}
}
