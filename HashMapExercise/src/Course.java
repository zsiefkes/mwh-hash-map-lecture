import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Course {
	private String courseCode;
	// These two aren't used
	private List<Student> enrolled = new ArrayList<>();
	private Set<Student> enrolledSet = new HashSet<>();
	
	private Map<Student, String> grades = new HashMap<>();
	
	public Course(String code) {
		courseCode = code;
	}
	
	public List<Student> getEnrolledStudents() {
		List<Student> l = new ArrayList<>();
		for (Map.Entry<Student, String> e : grades.entrySet()) {
			if (e.getValue() == null)
				l.add(e.getKey());
		}
		return l;
		//return enrolled; // not ideal practice!
	}
	
	public Map<Student, String> getGrades() {
		return grades;
	}
	
	public String getCode() {
		return courseCode;
	}
	
	public void assignGrade(Student s, String g) {
		grades.put(s, g);
	}
	
	public void addStudent(Student s) {
		//enrolled.add(s);
		grades.put(s, null);
	}
}
