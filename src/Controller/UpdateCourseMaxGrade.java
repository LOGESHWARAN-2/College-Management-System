package Controller;

import java.sql.SQLException;
import java.util.Scanner;


import Model.Database;
import Model.Operation;

public class UpdateCourseMaxGrade implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		System.out.println("Enter Course ID (-1 to show all your courses):");
		int cid = scanner.nextInt();
		while(cid<0) {
			new ReadEmployeeCourse().oper(database, scanner, id);
			System.out.println("Enter Course ID (-1 to shhow all your courses):");
			cid = scanner.nextInt();
		}
	
		System.out.println("Enter Max Grade:");
		double max = scanner.nextDouble();
		
		String Update = "Update `grades` SET `Max` = '"+max+"' WHERE `Course` = "+cid+" ; ";
		try {
			database.getStatement().execute(Update);
			System.out.println("Max Grade updated successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
