package gradeSystem;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * class: GradeSystem
 * 實現了所有學生的成績管理，包括讀取成績文件，查詢成績、排名、均分，更新配分等
 */
public class GradeSystem {
	/**
	 * 用一個 ArrayList 存儲所有學生的成績
	 * @uml.property  name="gradeArray"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="gradeSystem.Grade"
	 */
	private ArrayList<Grade> gradeArray = new ArrayList<Grade>();
	/**
	 * 默認配分，以百分比表示
	 * @uml.property  name="weights"
	 */
	private int[] weights = new int[]{10, 10, 10, 30, 40};

	/** 
	 * Constructor
	 * @throws FileNotFoundException, UnsupportedEncodingException
	 * pseudocode:
	 *     gradeArray <- readGrades(File)
	 *     for each grade in gradeArray
	 *         grade.calculateTotalGrade()
	 *     sort(gradeArray)
	 */
	GradeSystem(String gradeFile) throws FileNotFoundException, UnsupportedEncodingException {
		readGrades(gradeFile);
		calculateTotalGrades();
		sortGrades();
	}
	
	/**
	 * method: getNumStudents
	 * 獲取學生總數
	 * @return int 表示學生總數
	 */
	public int getNumStudents() {
		return gradeArray.size();
	}
	
	/**
	 * method: parseLine
	 * 解析單條成績記錄的每個字段
	 * @param String 來自原始文件的單行字符串
	 * @return String[] 表示單條成績記錄的每個字段
	 * pseudocode:
	 *     info <- split(line)
	 *     if info.length != 7
	 *         throw ParseException
	 *     else
	 *         return info
	 */
	public String[] parseLine(String line) throws ParseException {
		line = line.replaceAll("  ", "");
		String[] info = line.split(" ");
		if (info.length != 7) {
			throw new ParseException(line, line.length());
		}
		return info;
	}
	
	/**
	 * method: parseInfo
	 * 建立對應單條成績記錄的 Grade 對象
	 * @param String[] 單條成績記錄的每個字段
	 * pseudocode:
	 *     grade <- Grade(info)
	 */
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
	
	/**
	 * method: parseFile
	 * 解析文件的每一行
	 * @param Scanner 輸入掃描器
	 * pseudocode:
	 *     for each line from s
	 *         gradeArray[] <= parse(line)
	 *     handle Exceptions
	 */
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
	
	/**
	 * method: readGrades
	 * 從文件中讀取成績記錄
	 * @param String 儲存成績記錄的文件名
	 * @throws FileNotFoundException, UnsupportedEncodingException
	 * pseudocode:
	 *     s <- new Scanner()
	 *     parse(s)
	 *     handle Exceptions
	 */
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
	
	/**
	 * method: sortGrades
	 * 對 gradeArray 按照 totalGrade 字段進行排序，重載了 compare method 以實現降序排序
	 */
	public void sortGrades() {
		Collections.sort(gradeArray, new Comparator<Grade>() {
			@Override public int compare(Grade g1, Grade g2) {
				return Double.compare(g2.getTotalGrade(), g1.getTotalGrade());
			}
		});
	}
	
	/**
	 * method: calculateTotalGrades
	 * 根據當前配分更新所學生的 totalGrade
	 * pseudocode:
	 *     for each grade in grade Array
	 *         g.calcuateTotalGrade()
	 */
	private void calculateTotalGrades() {
		for (Grade g : gradeArray) {
			g.calcuateTotalGrade(weights);
		}
	}
	
	/**
	 * method: getWeights
	 * 獲取配分
	 * @return int[] 表示配分
	 * @uml.property  name="weights"
	 */
	public int[] getWeights() {
		return weights;
	}
	
	/**
	 * method: setWeights
	 * 更新配分并重新計算和排序成績
	 * @param int[] 新配分
	 * @uml.property  name="weights"
	 * pseudocode:
	 *     weights <- newWeights
	 *     gradeArray.calculateTotalGrades()
	 *     sort(gradeArray)
	 */
	private void setWeights(int[] newWeights) {
		weights = newWeights;
		calculateTotalGrades();
		sortGrades();
	}
	
	/**
	 * method: checkAndSetWeights
	 * 檢查新配分，若合法則更新配分
	 * @param int[] 新配分
	 * @return 一個 boolean，表示更新配分是否成功
	 * pseudocode:
	 *     check if weights sum up to 100%
	 *     check if weights are all non-negative
	 *     if conditions above are met
	 *         then setWeights()
	 *         return true
	 *     else
	 *         return false
     * 
	 */
	public boolean checkAndSetWeights(int[] weights) {
		boolean unitarity = (weights[0] + weights[1] + weights[2] + weights[3] + weights[4] == 100);  
		boolean nonnegative = (weights[0] >= 0 && weights[1] >= 0 && weights[2] >= 0 && weights[3] >= 0 && weights[4] >= 0);
		if (!unitarity || !nonnegative) return false;
		else {
			setWeights(weights);
			return true;
		}
	}
	
	/**
	 * method: containsID
	 * 檢查給定的 ID 是否存在
	 * @param String 慾查詢的 ID
	 * @return boolean 表示 ID 是否存在
	 * pseudocode:
	 *     for each grade in gradeArray
	 *     if grade.ID == ID
	 *         then return true
	 *     if not such grade
	 *         then return false
	 */
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
	
	/**
	 * method: getNameByID
	 * 根據 ID 獲取學生姓名
	 * @param String 慾查詢的 ID
	 * @return String 表示學生姓名
	 * pseudocode:
	 *     for each grade in gradeArray
	 *     if grade.ID == ID
	 *         then return grade.Name
	 *     if not such grade
	 *         then return ""
	 */
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
	
	/**
	 * method: getGrade
	 * 根據 ID 獲取學生成績
	 * @param String 慾查詢的 ID
	 * @return Grade 表示學生成績
	 * pseudocode:
	 *     for each grade in gradeArray
	 *     if grade.ID == ID
	 *         then return grade
	 *     if not such grade
	 *         then return empty Grade
	 */
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
	
	/**
	 * method: getRank
	 * 根據 ID 獲取學生排名
	 * @param String 慾查詢的 ID
	 * @return int 表示學生排名
	 * pseudocode:
	 *     for each grade in gradeArray
	 *     if grade.ID == ID
	 *         then return indexOf(grade)+1 as the rank
	 *     if not such grade
	 *         then return -1;
	 */
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
	
	/**
	 * method: getAverageGrade
	 * 獲取所有學生各項成績的平均分
	 * @return GradeAccumulator 表示各項平均分
	 * pseudocode:
	 *     for each grade in gradeArray
	 *         gradeAccumulator <+= grade
	 *     gradeAccumulator[:] /= [numStudents]
	 *     return gradeAccumulator
	 */
	public GradeAccumulator getAverageGrade() {
		GradeAccumulator ga = new GradeAccumulator();
		for (Grade g : gradeArray) {
			ga.addGrade(g);
		}
		ga.averageGrade(gradeArray.size());
		return ga;
	}
	
}
