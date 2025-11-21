package Controller;

import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;

public class ReadEmployeeCourse implements Operation {

	@Override
	public void oper(Database database, Scanner Scanner,int ID) {
		// TODO Auto-generated method stub
		for(Course c: new ReadCourses().getAllCourses(database)) {
			if(c.getProf().getID()==ID) {
				c.print();
			}
		}
	}

}
