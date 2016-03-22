package gradeSystem;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class GradeSystem {
	/**
	 * @uml.property  name="gradeArray"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="gradeSystem.Grade"
	 */
	private ArrayList<Grade> gradeArray = new ArrayList<Grade>();
	/**
	 * @uml.property  name="weights"
	 */
	private int[] weights = new int[]{10, 10, 10, 30, 40};

	GradeSystem(String gradeFile) throws FileNotFoundException, UnsupportedEncodingException {
		readGrades(gradeFile);
		calculateTotalGrades();
		sortGrades();
	}
	
	public int getNumStudents() {
		return gradeArray.size();
	}
	
	public String[] parseLine(String line) throws ParseException {
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
	
	public void readGrades(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		try {
			Scanner s = new Scanner(new InputStreamReader(new FileInputStream(new File(fileName)), "UTF8"));
			parseFile(s);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException();
		}
	}
	
	public void sortGrades() {
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
	
	/**
	 * @return
	 * @uml.property  name="weights"
	 */
	public int[] getWeights() {
		return weights;
	}
	
	/**
	 * @param newWeights
	 * @uml.property  name="weights"
	 */
	private void setWeights(int[] newWeights) {
		weights = newWeights;
		calculateTotalGrades();
		sortGrades();
	}
	
	public boolean checkAndSetWeights(int[] weights) {
		boolean unitarity = (weights[0] + weights[1] + weights[2] + weights[3] + weights[4] == 100);  
		boolean nonnegative = (weights[0] >= 0 && weights[1] >= 0 && weights[2] >= 0 && weights[3] >= 0 && weights[4] >= 0);
		if (!unitarity || !nonnegative) return false;
		else {
			setWeights(weights);
			return true;
		}
	}
	
	public boolean containsID(String ID) {
		boolean flag = false;
		for (Grade g : gradeArray) {
			if (ID.equals(g.getID())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public String getNameByID(String ID) {
		String name = "";
		for (Grade g : gradeArray) {
			if (ID.equals(g.getID())) {
				name = g.getName();
				break;
			}
		}
		return name;
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
		int rank = -1;
		for (int i = 0; i < gradeArray.size(); ++ i) {
			if (ID.equals(gradeArray.get(i).getID())) {
				rank = i + 1;
				break;
			}
		}
		return rank;
	}
	
	public GradeAccumulator getAverageGrade() {
		GradeAccumulator ga = new GradeAccumulator();
		for (Grade g : gradeArray) {
			ga.addGrade(g);
		}
		ga.averageGrade(gradeArray.size());
		return ga;
	}
	
}
