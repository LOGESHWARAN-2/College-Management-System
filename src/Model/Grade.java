package Model;

public class Grade {
	
		private int ID;
		private Course course;
		private Student student;
		private Class c;
		private double grade;
		private double max;
		
		public Grade() {
			
		}
		
		public int getID() {
			return ID;
		}
		public void setID(int ID) {
			this.ID=ID;
		}
		
		public Course getCourse() {
			return course;
		}
		
		public void setCourse(Course course) {
			this.course=course;
		}
		
		public Student getStudent() {
			return student;
		}
		
		public void setStudent(Student student) {
			this.student=student;
		}
		
		public Class getCurrentClass() {
			return c;
		}
		
		public void setClass(Class c) {
			this.c=c;
		}
		
		public double getGrade() {
			return grade;
		}

		public void setGrade(double grade) {
			this.grade = grade;
		}

		public double getMax() {
			return max;
		}

		public void setMax(double max) {
			this.max = max;
		}

		
}
