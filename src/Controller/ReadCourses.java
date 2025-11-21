package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Department;
import Model.Operation;
import Model.Class;
import Model.Employee;
public class ReadCourses implements Operation{

	@Override
	public void oper(Database database, Scanner scanner,int id) {
		
		for(Course c:  getAllCourses(database)) {
			c.print();
		}
	}
	
	public ArrayList<Course> getAllCourses(Database database){
		ArrayList<Course> cs = new ArrayList<>();
		ArrayList<Integer> cl = new ArrayList<>();
		ArrayList<Integer> pr = new ArrayList<>();
		ArrayList<Integer> depts = new ArrayList<>();
		
		String select = "SELECT *FROM `courses`;";
		try {
			
			ResultSet rs = database.getStatement().executeQuery(select);
			while(rs.next()) {
				Course c = new Course();
				c.setID(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				cl.add(rs.getInt("Class"));
				c.setDescription(rs.getString("Description"));
				c.setLimit(rs.getInt("Lim"));
				pr.add(rs.getInt("prof"));
				depts.add(rs.getInt("Department"));
				cs.add(c);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<cs.size();i++) {
			cs.get(i).setClass(new Class(cl.get(i),database));
			cs.get(i).setProf(new Employee(pr.get(i),database));
			cs.get(i).setDepartment(new Department(depts.get(i),database));
		}
		return cs;
		
	}
}
