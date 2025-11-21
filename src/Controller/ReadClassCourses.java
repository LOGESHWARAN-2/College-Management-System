package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
//import Model.Student;
import Model.Course;
public class ReadClassCourses implements Operation{

	@Override
	public void oper(Database database, Scanner Scanner, int id) {
		//Student st = new Student(id,database);
		ArrayList<Course> co = new ReadCourses().getAllCourses(database);
		System.out.println("ID\tName\t\tDescription\tProf\t\tDepartment");
		for(Course c: co ) {
				System.out.println();
				System.out.print(c.getID()+"\t");
				System.out.print(c.getName()+"\t\t");
				System.out.print(c.getDescription()+"\t");
				System.out.print("Dr. "+c.getProf().getFirstName()+" "+
				c.getProf().getLastName()+"\t");
				System.out.print(c.getDepartment().getName()+"\t");
				
			
		}
	}

}
