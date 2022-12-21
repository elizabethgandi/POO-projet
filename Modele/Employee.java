package Modele;

public abstract class Employee {
	private String name;
	private double weight;
	private double salary;
	protected double payload;// weight/8
	
	
	/**
	 * Construtor of Employee.
	 * @param name    employee's name.
	 * @param weight  employee's weight.
	 * @param salary  employee's salary.
	 */
	public Employee(String name, double weight, double salary)
	{
		this.name    = name;
		this.weight  = weight;
		this.salary  = salary;
		this.payload = this.weight/8;
	}
	
	
	
	// Getter : 
	
	public String getName() {
		return this.name;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getSalary() {
		return this.salary;
	}
	
	public double getPayload() {
		return this.payload;
	}
	
}
