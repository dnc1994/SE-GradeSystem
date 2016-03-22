package gradeSystem;

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
	
	GradeAccumulator() {
		avgLab1 = 0.0;
		avgLab2 = 0.0;
		avgLab3 = 0.0;
		avgMidTerm = 0.0;
		avgFinalExam = 0.0;
		avgTotalGrade = 0.0;
	}
	
	public void addGrade(Grade g) {
		avgLab1 += g.getLab1();
		avgLab2 += g.getLab2();
		avgLab3 += g.getLab3();
		avgMidTerm += g.getMidTerm();
		avgFinalExam += g.getFinalExam();
		avgTotalGrade += g.getTotalGrade();
	}
	
	public void averageGrade(int num_students) {
		if (num_students == 0) {
		    throw new IllegalArgumentException("Argument 'num_students' cannot be 0.");
		}
		avgLab1 /= num_students;
		avgLab2 /= num_students;
		avgLab3 /= num_students;
		avgMidTerm /= num_students;
		avgFinalExam /= num_students;
		avgTotalGrade /= num_students;
	}

	/**
	 * @return
	 * @uml.property  name="avgLab1"
	 */
	public double getAvgLab1() {
		return avgLab1;
	}
	
	/**
	 * @return
	 * @uml.property  name="avgLab2"
	 */
	public double getAvgLab2() {
		return avgLab2;
	}
	
	/**
	 * @return
	 * @uml.property  name="avgLab3"
	 */
	public double getAvgLab3() {
		return avgLab3;
	}
		
	/**
	 * @return
	 * @uml.property  name="avgMidTerm"
	 */
	public double getAvgMidTerm() {
		return avgMidTerm;
	}
	
	/**
	 * @return
	 * @uml.property  name="avgFinalExam"
	 */
	public double getAvgFinalExam() {
		return avgFinalExam;
	}
	
	/**
	 * @return
	 * @uml.property  name="avgTotalGrade"
	 */
	public double getAvgTotalGrade() {
		return avgTotalGrade;
	}
	
}
