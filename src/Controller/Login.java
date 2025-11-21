package Controller;

import Model.Database;
import Model.Employee;
import Model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Login {
	
	public static void main(String[]ar) {
		
		
		Scanner sca = new Scanner(System.in);
		Database database = new Database();
		System.out.println("Welcomwe to University Management System");
		System.out.println("1. Staff");
		System.out.println("2. Student");
		int ch = sca.nextInt();
		sca.nextLine();
		System.out.println("Enter email: ");
		String mail = sca.next();
		
		System.out.println("Enter password: ");
		String pass = sca.next();
		
		boolean loggedIn = false;
		
		if(ch==1) {
			String select = "SELECT ID, email, password FROM employees WHERE Email = '" + mail + "';";

			try {
				ResultSet rs = database.getStatement().executeQuery(select);
				while(rs.next()) {
					if(pass.equals(rs.getString("Password"))) {
						Employee e = new Employee(rs.getInt("ID"),database);
						loggedIn= true;
						e.showList(database, sca);
						break;
					}
				}
			}catch(SQLException e ) {
				e.printStackTrace();
			}
		}
		if(ch==2) {
			String select = "SELECT ID, email, password FROM students WHERE Email = '" + mail + "';";

			try {
				ResultSet rs = database.getStatement().executeQuery(select);
				while(rs.next()) {
					if(pass.equals(rs.getString("Password"))) {
						Student s = new Student(rs.getInt("ID"),database);
						loggedIn= true;
						s.showList(database, sca);
						break;
					}
				}
			}catch(SQLException e ) {
				e.printStackTrace();
			}
			
		}
		
		if(!loggedIn) {
			System.out.println("Wrong email or password. \n Try again later");
		}
		
	}

}
