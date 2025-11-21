package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadStudentData implements Operation {

	@Override
	public void oper(Database database, Scanner Scanner, int id) {
		Student st = new Student(id,database);
		st.print();
	}

}
