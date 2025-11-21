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

public class ReadCourseGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		System.out.println("Enter Course ID (-1 to show all your courses):");
		int cid = scanner.nextInt();
		while(cid<0) {
			new ReadEmployeeCourse().oper(database, scanner, id);
			System.out.println("Enter Course ID (-1 to shhow all your courses):");
			cid = scanner.nextInt();
		}
		
		Course c = new Course(cid,database);
		String select = "SELECT * FROM `grades` WHERE `Course` = "+c.getID()+"";
		ArrayList<Grade> gr = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			ArrayList<Integer> std = new ArrayList<>();
			
			while(rs.next()) {
				Grade g = new Grade();
				g.setID(rs.getInt("ID"));
				g.setCourse(c);
				g.setClass(c.getCurrentClass());
				std.add(rs.getInt("Student"));
				g.setGrade(rs.getDouble("Grade"));
				g.setMax(rs.getDouble("Max"));
				gr.add(g);
			}
			for(int i =0;i<gr.size();i++) {
				gr.get(i).setStudent(new Student(std.get(i),database));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(gr.size()!=0) {
			System.out.println("ID\tStudent\tClass\tGrade/"+gr.get(gr.size()-1).getMax());
		}
		for(Grade g: gr){
			System.out.println();
			System.out.print(g.getID()+"\t");
			System.out.print(g.getStudent().getFirstName()+" "+ g.getStudent().getLastName()+"\t");
			System.out.print(g.getCurrentClass().getName()+"\t");
			System.out.print(g.getGrade()+"\n");
		}
	}

}
