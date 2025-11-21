package Controller;

import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class RegisterCourse implements Operation{

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		Student st = new Student(id,database);
		System.out.println("Enter Course ID (-1 to show all courses):");
		int cid = scanner.nextInt();
		while(cid<0) {
			new ReadClassCourses().oper(database, scanner, id);
			System.out.println("\nEnter Course ID (-1 to show all courses):");
			cid = scanner.nextInt();
		}
		
		Course sl = new Course(cid,database);
		if(sl.getStudents().size() >=sl.getLimit()) {
			System.out.println("This course is full.\nTry again later");
		}
		else if(sl.getStudentsIDs().contains(st.getID())) {
			System.out.println("You have already registered this course.");
		}
		else 
		{
			st.registerCourse(database,cid);
		}
	}

}
