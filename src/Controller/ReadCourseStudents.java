package Controller;

import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadCourseStudents implements Operation{

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		System.out.println("Enter Course ID (-1 to show all my courses):");
		int cid = scanner.nextInt();
		while(cid<0) {
			new ReadEmployeeCourse().oper(database, scanner, id);
			System.out.println("Enter Course ID (-1 to show all my courses):");
			cid = scanner.nextInt();
		}
		
		Course c = new Course(cid,database);
		System.out.println("ID\tName\t\tEmail\tPhone Number\t\tBirth Date\tClass");
		for(Student s: c.getStudents()) {
			System.out.println();
			
			System.out.print(s.getID()+"\t");
			System.out.print(s.getFirstName()+" "+s.getLastName()+"\t");

			System.out.print(s.getEmail()+"\t");
			
			System.out.print(s.getPhoneNumber()+"\t\t");
			System.out.print(s.getBirthDate()+"\t");
			System.out.print(s.getCurrentClass().getName()+"\t");
			
			
		}
	}

}
