package jumpPlusProject3.model;

public class Student {
	private String name;
	private double grade;
	
	public Student(String name) {
		this.name = name;
		grade = 100; //Each Student starts out with a 100
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
