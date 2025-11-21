package Controller;

import java.util.Scanner;

import Model.Student;
import Model.Database;
import Model.Operation;

public class DeleteStudent implements Operation{

	@Override
	public void oper(Database database, Scanner scanner,int id) {
		// TODO Auto-generated method stub
		System.out.println("Enter Student ID (-1 to show all students)");
		int ID = scanner.nextInt();
		while(ID<0) {
			new Readstudents().oper(database, scanner,id);
			System.out.println("Enter Student ID (-1 to show all students)");
			 ID = scanner.nextInt();
		}
		Student s = new Student(ID);
		s.delete(database);
	}

}
