package jumpPlusProject3.model;

import java.util.ArrayList;
import java.util.List;

import jumpPlusProject3.util.Colors;

public class Class {
	private String name;
	private List<Student> students;
	private double average;
	
	public Class(String name) {
		this.name = name;
		students = new ArrayList<Student>();
	}
	
	public void printClass() {
		System.out.println(Colors.CYAN + "\nClass " + name + ":" + Colors.RESET);
		System.out.print(Colors.PURPLE);
		System.out.println("+----------+");
		System.out.println("| Students |");
		System.out.println("+----------+");
		System.out.print(Colors.CYAN);
		
		students.forEach(System.out::println);
		
		if(students.size() > 0)
			updateAverage();
		System.out.println(Colors.GREEN + "Class Average:" + average + Colors.RESET);
	}
	
	private void updateAverage() {
		average = students.stream().mapToDouble(Student::getGrade).average().getAsDouble();
	}
	
	public void addStudent(Student student) {
		students.add(student);		
	}
	
	public boolean removeStudent(String name) {
		return students.remove(students.stream().
				filter((student) -> (student.getName().equals(name))).
				findFirst().
				orElse(null));
	}
	
	public boolean changeGrade(String name, double grade) {
		Student s = students.stream().
				filter((student) -> (student.getName().equals(name))).
				findFirst().
				orElse(null);
		if(s != null) {
			s.setGrade(grade);
			return true;
		}
		else
			return false;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> studentsIn) {
		students = new ArrayList<Student>();
		studentsIn.stream().forEach((student) -> students.add(student));
	}
}
