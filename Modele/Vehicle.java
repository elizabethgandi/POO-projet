package Modele;

public abstract class Vehicle {
	private String name;
	private double price; //€
	protected double avgSpd; //Km/h
	protected double payloadCapa; // kg
	protected double prPerKm;// €/km
	protected double co2Emi; // g/kg
	protected String picSource;
	
	/**
	 * constructor that initializes the name, the price and the payload capacity.
	 * @param name  name of the vehicle
	 * @param price price of the vehicle
	 * @param payloadCapa payload capacity of the vehicle
	 */
	public Vehicle(String name, double price,double payloadCapa)
	{
		this.name        = name;
		this.price       = price;
		this.payloadCapa = payloadCapa;
	}
	
	/**
	 * Constructor that initializes the name and the price, 
	 * if the vehicle initializes the payload capacity itself.
	 * @param name  name of the vehicle 
	 * @param price price of the vehicle
	 */
	public Vehicle(String name, double price) {
		this.name        = name;
		this.price       = price;
	}
	
	// Getter : 
	
	public int getSpeed() {
		return (int)this.avgSpd;
	};
	

	public String getName(){
		return this.name;
	}

	public double getPrice(){
		return this.price;
	}

	public double getPayloadCapa() {
		return this.payloadCapa;
	}

	public double getPrPerKm() {
		return prPerKm;
	}

	public double getCo2Emi() {
		return co2Emi;
	}
	
	public String getPicSource() {
		String source = this.picSource;
		return source;
	}	
	
}
