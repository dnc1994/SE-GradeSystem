package gradeSystem;

public class Grade {
	private String ID;
	private String name;
	private int lab1;
	private int lab2;
	private int lab3;
	private int midTerm;
	private int finalExam;
	private double totalGrade;
	
	Grade() {
		// empty constructor
	}
	
	Grade(String _ID, String _name, int _lab1, int _lab2, int _lab3, int _midTerm, int _finalExam) {
		ID = _ID;
		name = _name;
		lab1 = _lab1;
		lab2 = _lab2;
		lab3 = _lab3;
		midTerm = _midTerm;
		finalExam = _finalExam;
		totalGrade = -1.0;
	}

	public String getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLab1() {
		return lab1;
	}
	
	public int getLab2() {
		return lab2;
	}
	
	public int getLab3() {
		return lab3;
	}
		
	public int getMidTerm() {
		return midTerm;
	}
	
	public int getFinalExam() {
		return finalExam;
	}
	
	public double getTotalGrade() {
		return totalGrade;
	}
	
	public void calcuateTotalGrade(int[] weights) {
		totalGrade = (weights[0] * lab1 + weights[1] * lab2 + weights[2] * lab3 + weights[3] * midTerm + weights[4] * finalExam) / 100.0;
	}
}
