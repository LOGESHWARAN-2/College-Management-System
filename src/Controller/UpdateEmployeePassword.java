package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Operation;

public class UpdateEmployeePassword  implements Operation{

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		Employee e = new Employee(id,database);
		System.out.println("Enter Old Password:");
		String old = scanner.next();
		
		if(!e.getPassword().equals(old)) {
			System.out.println("Wrong Password");
		}
		else {
			System.out.println("Enter New Password:");
			String newp = scanner.next();
			System.out.println("Confirm Password:");
			String con = scanner.next();
			while(!newp.equals(con)) {
				System.out.println("Enter New Password:");
				 newp = scanner.next();
				System.out.println("Confirm Password:");
				 con = scanner.next();
			}
			
			String Update = "UPDATE `employees` SET `Password` ='"+con+"' WHERE `ID` = "+e.getID()+";";
			try {
				database.getStatement().execute(Update);
				System.out.println("Password Updated");
			}catch(SQLException a) {
				a.printStackTrace();
			}
		}
		
	}

}
