import java.io.Serializable;

public class Employee implements Serializable{
	private String firstName;
	private String lastName;
	private int salary;
	private String title;
	private int id;
	
	public Employee(int id, String firstName, String lastName, int salary, String title){
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.title = title;
		this.id = id;
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

	public String getFullName() {
		return this.getFirstName().toUpperCase()+" "+this.getLastName().toUpperCase();
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}