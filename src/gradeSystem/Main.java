package gradeSystem;

public class Main {
	public static void main(String[] args) {
		GradeSystem test_system = new GradeSystem();
		
		Grade grade = test_system.getGrade("955002056");
		System.out.println(grade.getTotalGrade());
		
		AverageGrade ag = test_system.getAverageGrade();
		System.out.println(ag.getAvgTotalGrade());
		
		int rank = test_system.getRank("955002056");
		System.out.println(rank);
	}
}
