package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Controller.ReadAvailableCourses;
import Controller.ReadStudentData;
import Controller.ReadStudentGrades;
import Controller.RegisterCourse;
import Controller.UpdateStudentPassword;

public class Student {
	
	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String birthDate;
	private Model.Class c;
	private String password;
	
	public Student() {
		
	}
	
	public Student(int ID) {
			this.ID = ID;
	}

	public Student(int ID, Database database) {
	    this.ID = ID;
	    String select = "SELECT * FROM `students` WHERE `ID` = " + ID + ";";
	    
	    try {
	        ResultSet rs = database.getStatement().executeQuery(select);
	        
	        if (rs.next()) {
	            setFirstName(rs.getString("FirstName"));
	            setLastName(rs.getString("LastName"));
	            setEmail(rs.getString("Email"));
	            setPhoneNumber(rs.getString("PhoneNumber"));
	            setBirthDate(rs.getString("BirthDate"));
	            setPassword(rs.getString("Password"));
	            setID(rs.getInt("ID"));
	            // Ensure your Model.Class constructor supports this call
	            setClass(new Class(rs.getInt("Class"), database));
	        } else {
	            System.err.println("❌ No student found with ID: " + ID);
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Class getCurrentClass() {
		return c;
	}

	public void setClass(Class ca) {
		c = ca;
	}
	public void print() {
		System.out.println("ID:\t"+getID());
		System.out.println("FirstName:\t\t"+getFirstName());
		System.out.println("LastName:\t\t"+getLastName());
		System.out.println("Email\t\t:"+getEmail());
		System.out.println("PhoneNumber:\t\t"+getPhoneNumber());
		System.out.println("BirthDate:"+getBirthDate());
		System.out.println("Class:\t\t"+c.getName());
		System.out.println("------------------------------------\n");
		
	}

	public void create(Database database) {
		
		String insert = "INSERT INTO Students "
	              + "(ID, FirstName, LastName, Email, PhoneNumber, BirthDate, Class, Password) VALUES ("
	              + ID + ", '"
	              + firstName + "', '"
	              + lastName + "', '"
	              + email + "', '"
	              + phoneNumber + "', '"
	              + birthDate + "', "
	              + c.getID() + ", '"
	              + password + "');";

			try {
				database.getStatement().execute(insert);
				System.out.println("Student added succesfully");
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password= password;
	}
	
	public void update(Database database) {
		String update = "UPDATE `students` SET " +
			    "`FirstName`='" + firstName + "', " +
			    "`LastName`='" + lastName + "', " +
			    "`Email`='" + email + "', " +
			    "`PhoneNumber`='" + phoneNumber + "', " +
			    "`BirthDate`='" + birthDate + "', " +
			    "`Class`='" + c.getID() + "', " +
			    "`Password`='" + password + "' " +
			    "WHERE `ID`=" + ID + ";";

		
		try {
			database.getStatement().execute(update);
			System.out.println("Student updated succesfully");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Database database) {
		// TODO Auto-generated method stub
		String delete = "DELETE FROM `students` WHERE `ID` = "+ID+";";
		
		try {
			database.getStatement().execute(delete);
			System.out.println("Student data deteled succesfully");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private Operation[] operations = new Operation[] {
			new RegisterCourse(),
			new ReadStudentData(),
			new ReadAvailableCourses(),
			new ReadStudentGrades(),
			new UpdateStudentPassword()
	};
	public void showList( Database database,Scanner scanner) {
		System.out.println("_________________________\n");
		System.out.println("01. Register Courses");
		System.out.println("02. Show My Data");
		System.out.println("03. Show Available Courses");
		System.out.println("04. Show My Grades");
		System.out.println("05. Change Password");
		System.out.println("\n_______________________");
		
		int selected = scanner.nextInt();
		operations[selected-1].oper(database, scanner,getID());
		showList(database,scanner);
	}

	public void registerCourse(Database database, int cid) {
		String insert = "INSERT INTO `course " + cid + "` (`Student`) VALUES ('" + getID() + "');";

		try {
				database.getStatement().execute(insert);
				System.out.println("Course registered successfully");
		 } catch (SQLException e) {
				e.printStackTrace();
		    }
	}
}
