package jumpPlusProject3.model;

import java.util.ArrayList;
import java.util.List;

import jumpPlusProject3.util.Colors;

public class Class {
	private String name;
	private List<Student> students;
	
	public Class(String name) {
		this.name = name;
		students = new ArrayList<Student>();
	}
	
	public void printClass() {
		System.out.println(Colors.GREEN + "Class " + name + ":" + Colors.RESET);
		System.out.println(Colors.PURPLE);
		System.out.println("+----------+");
		System.out.println("| Students |");
		System.out.println("+----------+");
		System.out.println(Colors.CYAN);
		
		students.forEach(System.out::println);
		
		System.out.println(Colors.RESET);
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
