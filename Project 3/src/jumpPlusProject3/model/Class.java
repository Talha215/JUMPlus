package jumpPlusProject3.model;

import java.util.ArrayList;
import java.util.List;

public class Class {
	private String name;
	private List<Student> students;
	
	public Class(String name) {
		this.name = name;
		students = new ArrayList<Student>();
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	
	public String getName() {
		return name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
}
