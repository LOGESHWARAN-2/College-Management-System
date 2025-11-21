package Controller;

import java.util.Scanner;
import Model.Database;
import Model.Department;
import Model.Employee;
import Model.Operation;

public class UpdateEmployee implements Operation{

	@Override
	public void oper(Database database, Scanner scanner,int id) {
		
			System.out.println("Enter user ID(-1 to show all employees):");
			int userID = scanner.nextInt();		
					
			while(userID<0) {
				new ReadEmployees().oper(database, scanner,id);
				userID=scanner.nextInt();
			}
		Employee employee = new Employee(userID,database);
		
		System.out.println("Enter First Name (-1 to keep "+employee.getFirstName()+"):");
		String firstname = scanner.next();
		if(!firstname.equals("-1")) {
			employee.setFirstName(firstname);
		}
		
		System.out.println("Enter Last Name (-1 to keep "+employee.getLastName()+"):");
		String lastname = scanner.next();
		if(!lastname.equals("-1")) {
			employee.setLastName(lastname);
		}
	
		System.out.println("Enter email (-1 to keep "+employee.getEmail()+")");
		String email = scanner.next();
		if(!email.equals("-1")) {
			employee.setEmail(email);
		}
		
		System.out.println("Enter PhoneNumber (-1 to keep "+employee.getPhoneNumber()+"):");
		String number = scanner.next();
		if(!number.equals("-1")) {
			employee.setPhoneNumber(number);
		}
		
		System.out.println("Enter BirthDate (-1 to keep "+employee.getBirthDate()+"):");
		String date = scanner.next();
		if(!date.equals("-1")) {
			employee.setBirthDate(date);
		}
		
		System.out.println("Enter Salary (-1 to keep "+employee.getSalary()+"):");
		double salary = scanner.nextDouble();
		if(salary!=-1) {
			employee.setSalary(salary);
		}
		
		System.out.println("Enter Password (-1 to keep old password):");
		String pass = scanner.next();
		if(!pass.equals("-1")) {
			System.out.println("confirm password:");
			String con= scanner.next();
			while(!pass.equals(con)) {
				System.out.println("Enter Password:");
				pass = scanner.next();
				System.out.println("confirm password:");
				 con= scanner.next();
			}
			employee.setPassword(pass);
		}
		
		System.out.println("Enter Department ID (-1 to keep "+employee.getDepartment().getName()+")\n"
				+"(-2 to show all departments):");
		int deptID = scanner.nextInt();
		if(deptID==-1) {
			while(deptID==-2) {
				new ReadDepartments().oper(database, scanner,id);
				deptID = scanner.nextInt();
			}
			employee.setDepartment(new Department(deptID,database));
		}
		
		employee.update(database);
	}

}
