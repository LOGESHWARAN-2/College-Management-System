package Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Course {
	
	private int ID;
	private String name;
	private Class c;
	private String description;
	private int limit;
	private ArrayList<Student> students; 
	private ArrayList<Integer> studentIDs; 
	
	private Employee prof;
	private Department dept;

	public Course() {
		students = new ArrayList<>();
		studentIDs = new ArrayList<>();
	}
	
	public Course(int ID, Database database) {
		students = new ArrayList<>();
		studentIDs = new ArrayList<>();
		setID(ID);

		String select1 = "SELECT * FROM `courses` WHERE `ID` = " + ID + ";";
		String select2 = "SELECT * FROM `course " + ID + "`;";   // ✅ removed space

		try {
			ResultSet rs = database.getStatement().executeQuery(select1);
			if (rs.next()) {
				setName(rs.getString("Name"));
				int classID = rs.getInt("Class");
				setDescription(rs.getString("Description"));
				setLimit(rs.getInt("Lim")); // ✅ make sure column is Lim, not Limit
				int profID = rs.getInt("Prof");
				int deptID = rs.getInt("Department");

				setClass(new Class(classID, database));
				setProf(new Employee(profID, database));
				setDepartment(new Department(deptID, database));
			}

			ResultSet rs2 = database.getStatement().executeQuery(select2);
			while (rs2.next()) {
				studentIDs.add(rs2.getInt("Student"));
			}
			for (Integer i : studentIDs) {
				students.add(new Student(i, database));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Course(int ID) {
		this.ID = ID;
		students = new ArrayList<>();
		studentIDs = new ArrayList<>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getCurrentClass() {
		return c;
	}

	public void setClass(Class c) {
		this.c = c;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Integer> getStudentsIDs() {
		return studentIDs;
	}

	public Employee getProf() {
		return prof;
	}

	public void setProf(Employee prof) {
		this.prof = prof;
	}

	public Department getDepartment() {
		return dept;
	}

	public void setDepartment(Department dept) {
		this.dept = dept;
	}

	public void print() {
		System.out.println("ID:\t" + getID());
		System.out.println("Name of the course: " + getName());
		System.out.println("Class: " + getCurrentClass().getName());
		System.out.println("Description of the course: " + getDescription());
		System.out.println("Limit for the course: " + getLimit());
		System.out.println("Professor: " + getProf().getFirstName() + " " + getProf().getLastName());
		System.out.println("Department: " + getDepartment().getName());
		System.out.println("__________________________________________________\n");
	}

	public void create(Database database) {
		String insert = "INSERT INTO `courses` (`ID`, `Name`, `Class`, `Description`, `Lim`, `Prof`, `Department`) VALUES ('"
				+ getID() + "', '" + getName() + "', '" + getCurrentClass().getID() + "', '" + getDescription() + "', '"
				+ getLimit() + "', '" + getProf().getID() + "', '" + getDepartment().getID() + "')";
		
		String create = "CREATE TABLE `course " + getID() + "` (Student INT);";  // ✅ removed space

		try {
			database.getStatement().execute(insert);
			database.getStatement().execute(create);

			System.out.println("Course created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Database database) {
		String up = "UPDATE courses SET Name='" + getName() + "', Class='" + getCurrentClass().getID()
				+ "', Description='" + getDescription() + "', Lim='" + getLimit() + "', Prof='" + getProf().getID()
				+ "', Department='" + getDepartment().getID() + "' WHERE ID = " + getID() + ";";
		try {
			database.getStatement().execute(up);
			System.out.println("Course updated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Database database) {
		String delete = "DELETE FROM `courses` WHERE `ID` = " + getID() + ";";
		String drop = "DROP TABLE `course " + getID() + "`;";   // ✅ removed space

		try {
			database.getStatement().execute(delete);
			database.getStatement().execute(drop);

			System.out.println("Course deleted successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
