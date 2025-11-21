package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadAvailableCourses implements Operation{

	@Override
	public void oper(Database database, Scanner Scanner, int id) {
		
		Student st = new Student(id,database);
		ArrayList<Course> co = new ReadCourses().getAllCourses(database);
		for (Course c : co) {
		    if (c.getCurrentClass().getID() == st.getCurrentClass().getID() && c.getStudents().size() < c.getLimit()) {
		        c.print();
		    }
		}

		
	}

}
