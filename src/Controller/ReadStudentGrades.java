package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Grade;
import Model.Operation;
import Model.Student;
import Model.Class;

public class ReadStudentGrades  implements Operation{

	@Override
	public void oper(Database database, Scanner Scanner, int id) {
		String Select = "SELECT `ID`, `Course`, `ClassID`, `Grade`, `Max` FROM `grades` WHERE `Student`=" + id + ";";

		ArrayList<Grade> grades = new ArrayList<>();

		try {
		    ResultSet rs = database.getStatement().executeQuery(Select);
		    ArrayList<Integer> coursesIDs = new ArrayList<>();
		    ArrayList<Integer> classesIDs = new ArrayList<>();

		    while (rs.next()) {
		        Grade g = new Grade();
		        g.setID(rs.getInt("ID"));
		        coursesIDs.add(rs.getInt("Course"));
		        classesIDs.add(rs.getInt("ClassID")); // works if column exists and backticks are used
		        g.setGrade(rs.getDouble("Grade"));
		        g.setMax(rs.getDouble("Max"));
		        grades.add(g);
		    }

		    Student student = new Student(id, database);
		    for (int i = 0; i < grades.size(); i++) {
		        grades.get(i).setCourse(new Course(coursesIDs.get(i), database));
		        grades.get(i).setClass(new Class(classesIDs.get(i), database));
		        grades.get(i).setStudent(student);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		
		System.out.println("ID\tCourse\tClass\tGrade\tMax");
		for (Grade g : grades) {
			System.out.println();
		    System.out.print(g.getID() + "\t");
		    System.out.print(g.getCourse().getName() + "\t");
		    System.out.print(g.getCurrentClass().getName() + "\t");
		    System.out.print(g.getGrade() + "\t");
		    System.out.print(g.getMax() + "\t");
		}

	}

}
