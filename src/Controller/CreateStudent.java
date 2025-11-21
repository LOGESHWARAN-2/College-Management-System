package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import Model.Class;
import Model.Database;

import Model.Operation;
import Model.Student;

public class CreateStudent implements Operation {

	@Override
	public void oper(Database database, Scanner scanner,int id) {
		// TODO Auto-generated method stub
		Student s = new Student();
		System.out.println("Enter First Name:");
		s.setFirstName(scanner.next());
		
		System.out.println("Enter Last Name:");
		s.setLastName(scanner.next());
		
		System.out.println("Enter Email:");
		s.setEmail(scanner.next());
		
		System.out.println("Enter PhoneNumber:");
		s.setPhoneNumber(scanner.next());
		
		System.out.println("Enter BirthDate:");
		s.setBirthDate(scanner.next());
		
		System.out.println("Enter Class ID (-1 to show all classes):");
		int ID = scanner.nextInt();
		while(ID<0) {
			new ReadClasses().oper(database, scanner,id);
			System.out.println("Enter Class ID (-1 to show all classes):");
			ID = scanner.nextInt();
		}
		
		s.setClass(new Class(ID,database));
		
		System.out.println("Enter password");
		String password = scanner.next();
		System.out.println("Confirm password");
		String conpassword = scanner.next();
		
		while(!password.equals(conpassword)) {
			System.out.println("password doesn't match");
			System.out.println("Enter password");
			password = scanner.next();
			System.out.println("Confirm password");
			conpassword = scanner.next();
		}
		s.setPassword(password);
		ArrayList<Student> stu = new Readstudents().getAllStudents(database);
	    int Id = 0;
	    if(stu.size()!=0) {
	    	Id= stu.get(stu.size()-1).getID()+1;
	    }
		s.setID(Id);
		s.create(database);
	}

}
