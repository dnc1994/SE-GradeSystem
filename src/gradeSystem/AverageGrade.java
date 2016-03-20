package gradeSystem;

public class AverageGrade {
	private double avgLab1;
	private double avgLab2;
	private double avgLab3;
	private double avgMidTerm;
	private double avgFinalExam;
	private double avgTotalGrade;
	
	AverageGrade() {
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
		avgLab1 /= num_students;
		avgLab2 /= num_students;
		avgLab3 /= num_students;
		avgMidTerm /= num_students;
		avgFinalExam /= num_students;
		avgTotalGrade /= num_students;
	}

	public double getAvgLab1() {
		return avgLab1;
	}
	
	public double getAvgLab2() {
		return avgLab2;
	}
	
	public double getAvgLab3() {
		return avgLab3;
	}
		
	public double getAvgMidTerm() {
		return avgMidTerm;
	}
	
	public double getAvgFinalExam() {
		return avgFinalExam;
	}
	
	public double getAvgTotalGrade() {
		return avgTotalGrade;
	}
	
}
