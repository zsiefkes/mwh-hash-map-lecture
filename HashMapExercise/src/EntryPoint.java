import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EntryPoint {

	private Scanner input = new Scanner(System.in);
	private Map<String, Student> students = new HashMap<>();
	private Map<Integer, Student> studentsById = new HashMap<>();
	private Map<String, Course> courses = new HashMap<>();
	
	public EntryPoint() {
		loadData();
		System.out.println("?");
		String command = input.nextLine();
		while (true) {
			if ("addStudent".equals(command)) {
				addStudent();
			} else if ("addCourse".equals(command)) {
				addCourse();
			} else if ("lookUpStudent".equals(command)) {
				lookUpStudent();
			} else if ("lookUpByID".equals(command)) {
				lookUpByID();
			} else if ("enrol".equals(command)) {
				enrol();
			} else if ("grade".equals(command)) {
				grade();
			} else if ("lookAtCourse".equals(command)) {
				lookAtCourse();
			} else if ("quit".equals(command)) {
				return;
			}
			System.out.println("?");
			command = input.nextLine();			
		}
	}
	
	private void loadData() {
		try {
			Scanner scan = new Scanner(new File("data.txt"));
			while (scan.hasNext()) {
				String code = scan.next();
				String grade = scan.next();
				String name = scan.nextLine().trim();
				
				Course c;
				if (!courses.containsKey(code)) {
					c = new Course(code);
					courses.put(code, c);
				} else {
					c = courses.get(code);
				}
				Student s = students.get(name);
				if (s == null) {
					s = new Student(name, (int)(Math.random() * 10000000));
					students.put(name, s);
				}
				
				if ("-".equals(grade)) {
					c.addStudent(s);
				} else {
					c.assignGrade(s, grade);
					s.receiveGrade(c, grade);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void lookAtCourse() {
		System.out.println("Course code: ");
		String code = input.nextLine();		
		Course c = courses.get(code);
		if (c == null) {
			System.out.println("No such course.");
			return;
		}
		
		System.out.println("Course: " + c.getCode());
		System.out.println("Enrolled students:");
		for (Student s : c.getEnrolledStudents()) {
			System.out.println("  " + s.getName());
		}
		System.out.println("Grades:");
		for (Map.Entry<Student, String> e : c.getGrades().entrySet()) {
			System.out.println("  " +
					e.getKey().getName() + "\t" +
					e.getValue());
		}
	}
	
	private void enrol() {
		System.out.println("Student's name: ");
		String name = input.nextLine();
		System.out.println("Course code: ");
		String code = input.nextLine();
		
		Course c = courses.get(code);
		if (c == null) {
			System.out.println("No such course.");
			return;
		}
		Student s = students.get(name);
		if (s == null) {
			System.out.println("No such student.");
			return;
		}
		c.addStudent(s);
	}
	
	private void grade() {
		System.out.println("Student's name: ");
		String name = input.nextLine();
		System.out.println("Course code: ");
		String code = input.nextLine();
		
		Course c = courses.get(code);
		if (c == null) {
			System.out.println("No such course.");
			return;
		}
		Student s = students.get(name);
		if (s == null) {
			System.out.println("No such student.");
			return;
		}
		
		System.out.println("Grade for " + s.getName() + " in "
				+ c.getCode() + ":");
		String grade = input.nextLine();
		
		s.receiveGrade(c, grade);
		c.assignGrade(s, grade);
	}
	
	private void addStudent() {
		System.out.println("Student's name: ");
		String name = input.nextLine();
		Student s = new Student(name, (int)(Math.random() * 10000000));
		students.put(name, s);
		studentsById.put(s.getID(), s);
	}
	
	private void addCourse() {
		System.out.println("Course code: ");
		String name = input.nextLine();
		Course s = new Course(name);
		courses.put(name, s);
	}
	
	private void lookUpStudent() {
		System.out.println("Student's name: ");
		String name = input.nextLine();
		Student s = students.get(name);
		if (s == null) {
			System.out.println("No such student.");
		} else {
			System.out.println(s.getInfo());
		}
	}
	private void lookUpByID() {
		System.out.println("Student's ID: ");
		int id = input.nextInt();
		input.nextLine();
		Student s = studentsById.get(id);
		if (s == null) {
			System.out.println("No such student.");
		} else {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) {
		new EntryPoint();
	}

}
