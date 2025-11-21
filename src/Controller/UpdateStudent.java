package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Student;
import Model.Class;

public class UpdateStudent implements Operation {

	@Override
	public void oper(Database database, Scanner scanner,int id) {

			System.out.println("Enter ID (-1 to show all Students):");
			int ID = scanner.nextInt();
			while(ID<0) {
				new Readstudents().oper(database,scanner,id);
				System.out.println("Enter ID (-1 to show all Students):");
				ID = scanner.nextInt();
			}
		Student s =  new Student(ID,database);
		
		System.out.println("Enter FirstName(-1 to keep "+s.getFirstName()+"):");
		String firstName = scanner.next();
		if(!firstName.equals("-1")) {
			s.setFirstName(firstName);
		}
		
		System.out.println("Enter LastName(-1 to keep "+s.getLastName()+"):");
		String lastName = scanner.next();
		if(!lastName.equals("-1")) {
			s.setLastName(lastName);
		}
		
		System.out.println("Enter Email(-1 to keep "+s.getEmail()+"):");
		String Email = scanner.next();
		if(!Email.equals("-1")) {
			s.setEmail(Email);
		}
		
		System.out.println("Enter Phone Number(-1 to keep "+s.getPhoneNumber()+"):");
		String phoneNumber = scanner.next();
		if(!phoneNumber.equals("-1")) {
			s.setPhoneNumber(phoneNumber);
		}
		
		System.out.println("Enter BirthDate(-1 to keep "+s.getBirthDate()+"):");
		String birth = scanner.next();
		if(!birth.equals("-1")) {
			s.setBirthDate(birth);
		}
		
		System.out.println("Enter Class ID(-1 to keep "+s.getCurrentClass().getName()+")\n"+"(-2 to show all classes):");
		int classID  = scanner.nextInt();
		if(classID!=-1) {
			while(classID==-2) {
				new ReadClasses().oper(database, scanner,id);
				System.out.println("Enter Class ID (-2 to show all classes):");
				 classID  = scanner.nextInt();
			}
			s.setClass(new Class(classID,database));
		}
		
		System.out.println("Enter password(-1 to keep old one):");
		String pass = scanner.next();
		if(!pass.equals("-1")) {
			System.out.println("Confirm password:");
			String conpass = scanner.next();
				while(!pass.equals(conpass)) {
					System.out.println("Enter password:");
					pass = scanner.next();
					System.out.println("Confirm password:");
					conpass = scanner.next();
				}
				s.setPassword(conpass);
		}
		
		s.update(database);
	}

}
