package Modele;

public abstract class Cyclist extends Employee {
	protected int level; //Cyclist's physical condition 
	protected double speed;
	
	/**
	 * Constructor
 	 * @param name name of the cyclist
	 * @param weight weight of the cyclist
	 * @param salary salary of the cyclist
	 */
	public Cyclist(String name, double weight, double salary) {
		super(name, weight, salary);
	}

	// Getter :
	
	public int getLevel() {
		return this.level;
	}
	
	public double getSpeed(){
        return this.speed;
    }

}
