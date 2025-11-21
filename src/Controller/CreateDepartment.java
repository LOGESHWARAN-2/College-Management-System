package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Operation;

public class CreateDepartment implements Operation {

	@Override
	public void oper(Database database, Scanner scanner,int id) {
		
		scanner.nextLine();
		System.out.println("Enter Department Name:");
		String deptName = scanner.nextLine();
		
		int ID =0;
		ArrayList<Department> departments = new ReadDepartments().getAllDepartments(database);
		if(departments.size()!=0) {
			ID= departments.get(departments.size()-1).getID()+1;
			
		}
	
		Department dept = new Department();
		dept.setID(ID);
		dept.setName(deptName);
		dept.create(database);
	}

}
