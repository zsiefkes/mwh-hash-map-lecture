import java.util.HashMap;
import java.util.Map;

public class Student {
	private String name;
	private int id;
	
	private Map<String, String> grades = new HashMap<>();
	
	//private Map<Course, String> grades2 = new HashMap<>();
	
	public Student(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public void receiveGrade(Course c, String g) {
		grades.put(c.getCode(), g);
	}
	
	public String toString() {
		return name + " (" + id + ")";
	}
	
	public String getInfo() {
		String ret = name + " (" + id + ")";
		for (String code : grades.keySet()) {
			ret = ret + "\n" + grades.get(code) + "\t" + code;
		}
		return ret;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
}
