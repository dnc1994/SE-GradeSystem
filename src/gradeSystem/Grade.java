package gradeSystem;

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
	 * @return
	 * @uml.property  name="iD"
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return
	 * @uml.property  name="lab1"
	 */
	public int getLab1() {
		return lab1;
	}
	
	/**
	 * @return
	 * @uml.property  name="lab2"
	 */
	public int getLab2() {
		return lab2;
	}
	
	/**
	 * @return
	 * @uml.property  name="lab3"
	 */
	public int getLab3() {
		return lab3;
	}
		
	/**
	 * @return
	 * @uml.property  name="midTerm"
	 */
	public int getMidTerm() {
		return midTerm;
	}
	
	/**
	 * @return
	 * @uml.property  name="finalExam"
	 */
	public int getFinalExam() {
		return finalExam;
	}
	
	/**
	 * @return
	 * @uml.property  name="totalGrade"
	 */
	public double getTotalGrade() {
		return totalGrade;
	}
	
	public void calcuateTotalGrade(int[] weights) {
		totalGrade = (weights[0] * lab1 + weights[1] * lab2 + weights[2] * lab3 + weights[3] * midTerm + weights[4] * finalExam) / 100.0;
	}
}
