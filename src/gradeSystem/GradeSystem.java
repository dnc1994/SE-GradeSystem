package gradeSystem;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class GradeSystem {
	private ArrayList<Grade> gradeArray = new ArrayList<Grade>();
	private double[] weights = new double[]{0.1, 0.1, 0.1, 0.3, 0.4};

	GradeSystem() {
		readGrades("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput.txt");
		calculateTotalGrades();
		sortGrades();
	}
	
	private String[] parseLine(String line) throws ParseException {
		line = line.replaceAll("  ", "");
		String[] info = line.split(" ");
		if (info.length != 7) {
			throw new ParseException(line, line.length());
		}
		return info;
	}
	
	private Grade parseInfo(String[] info) {
		String ID = info[0];
		String name = info[1];
		int lab1 = Integer.parseInt(info[2]);
		int lab2 = Integer.parseInt(info[3]);
		int lab3 = Integer.parseInt(info[4]);
		int midTerm = Integer.parseInt(info[5]);
		int finalExam = Integer.parseInt(info[6]);
		return new Grade(ID, name, lab1, lab2, lab3, midTerm, finalExam);
	}
	
	private void parseFile(Scanner s) {
		while (s.hasNextLine()) {
			try {
				gradeArray.add(parseInfo(parseLine(s.nextLine())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		s.close();
	}
	
	private void readGrades(String fileName) {
		try {
			Scanner s = new Scanner(new InputStreamReader(new FileInputStream(new File(fileName)), "UTF8"));
			parseFile(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void sortGrades() {
		Collections.sort(gradeArray, new Comparator<Grade>() {
			@Override public int compare(Grade g1, Grade g2) {
				return Double.compare(g2.getTotalGrade(), g1.getTotalGrade());
			}
		});
	}
	
	private void calculateTotalGrades() {
		for (Grade g : gradeArray) {
			g.calcuateTotalGrade(weights);
		}
	}
	
	public double[] getWeights() {
		return weights;
	}
	
	public void setWeights(double[] newWeights) {
		weights = newWeights;
		calculateTotalGrades();
		sortGrades();
	}
	
	public boolean containsID(String[] ID) {
		boolean flag = false;
		for (Grade g : gradeArray) {
			if (ID.equals(g.getID())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public Grade getGrade(String ID) {
		Grade ret = new Grade();
		for (Grade g : gradeArray) {
			if (ID.equals(g.getID())) {
				ret = g;
				break;
			}
		}
		return ret;
	}
	
	public int getRank(String ID) {
		int rank = 0;
		for (int i = 0; i < gradeArray.size(); ++ i) {
			if (ID.equals(gradeArray.get(i).getID())) {
				rank = i + 1;
				break;
			}
		}
		return rank;
	}
	
	public AverageGrade getAverageGrade() {
		AverageGrade ag = new AverageGrade();
		for (Grade g : gradeArray) {
			ag.addGrade(g);
		}
		ag.averageGrade(gradeArray.size());
		return ag;
	}
	
}
