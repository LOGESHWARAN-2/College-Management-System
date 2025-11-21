package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class AddCourseGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		System.out.println("Enter Course ID (-1 to shhow all your courses):");
		int cid = scanner.nextInt();
		while(cid<0) {
			new ReadEmployeeCourse().oper(database, scanner, id);
			System.out.println("Enter Course ID (-1 to shhow all your courses):");
			cid = scanner.nextInt();
		}
		
		Course c = new Course(cid,database);
		System.out.println("Enter max (double):");
		double max = scanner.nextDouble();
		
		System.out.println("Type the grade for each name of these students (double):");
		ArrayList<Student> st = c.getStudents();
		
		
		for (int i = 0; i < st.size(); i++) {
		    System.out.println(st.get(i).getFirstName() + " " + st.get(i).getLastName());
		    double grade = scanner.nextDouble();
		    String insert = "INSERT INTO `grades`(`Course`, `ClassID`, `Student`, `Grade`, `Max`) " +
		                    "VALUES ('" + c.getID() + "', '" + c.getCurrentClass().getID() + "', '" + st.get(i).getID() + "', '" + grade + "', '" + max + "');";
		    try {
		        database.getStatement().execute(insert);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		System.out.println("Grades added Successfully");

	}

}
