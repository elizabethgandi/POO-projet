package Modele;

public class Meal {
	private double weight;
	private double distance;
	
	/**
	 * Constructor of meal.
	 * @param weight
	 * @param distance
	 */
	public Meal(double weight,double distance) {
		this.weight = weight;
		this.distance = distance;//en km
	}
	
	/**
	 * Getter of weight.
	 * @return
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/**
	 * Getter of distance.
	 * @return
	 */
	public double getdistance() {
		return this.distance;
	}
}
