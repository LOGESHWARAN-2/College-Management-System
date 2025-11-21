package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Model.Database;
import Model.Department;
import Model.Employee;
import Model.Operation;

public class ReadEmployees implements Operation{

	@Override
	public void oper(Database database, Scanner Scanner,int id) {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = getAllEmployees(database);
		for(Employee e : employees) {
			e.print();
		}
	}
	
	public ArrayList<Employee> getAllEmployees(Database database){
		ArrayList<Employee> employees = new ArrayList<>();
		ArrayList<Integer> deptIDs = new ArrayList<>();
		
		try {
		String select ="SELECT *FROM `employees`;";
		ResultSet rs = database.getStatement().executeQuery(select);
		while(rs.next()) {
			Employee emp = new Employee();
			emp.setID(rs.getInt("ID"));
			emp.setFirstName(rs.getString("FirstName"));
			emp.setLastName(rs.getString("LastName"));
			emp.setEmail(rs.getString("Email"));
			emp.setPhoneNumber(rs.getString("PhoneNumber"));
			emp.setBirthDate(rs.getString("BirthDate"));
			emp.setSalary(rs.getDouble("Salary"));
			emp.setPassword(rs.getString("Password"));
			employees.add(emp);
			deptIDs.add(rs.getInt("Department"));
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		for(int i=0;i<employees.size();i++) {
			employees.get(i).setDepartment(new Department(deptIDs.get(i),database));
		}
		
		return employees;
	}

}
