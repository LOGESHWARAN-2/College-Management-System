package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class DeleteCourseGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		
		System.out.println("Enter Course ID (-1 to show all your courses):");
		int cid = scanner.nextInt();
		while(cid<0) {
			new ReadEmployeeCourse().oper(database, scanner, id);
			System.out.println("Enter Course ID (-1 to shhow all your courses):");
			cid = scanner.nextInt();
		}
		
		String delete = " DELETE FROM `grades` WHERE `Course` = "+cid+"; ";
		try {
			database.getStatement().execute(delete);
			System.out.println("Grade Deleted Successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
