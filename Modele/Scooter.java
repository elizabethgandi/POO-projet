package Modele;

public class Scooter extends Vehicle {
	private double cylinder;
	private double gasConsu;
	public final static double prGas = 1.65;
	
	/**
	 * 
	 * @param name  name of the scooter. 
	 * @param price  price of the scooter.
	 * @param payloadCapa  payload capacity of the scooter.
	 * @param cylinder cylinder of the scooter wich impact the speed and the co2 emission.
	 */
	public Scooter (String name, double price,double payloadCapa, double cylinder,double gasConsu){
		super(name, price,payloadCapa);
		this.cylinder = cylinder;
		this.co2Emi   = this.cylinder/4; //g/km
		this.avgSpd = 30 + this.cylinder/50;
		this.gasConsu = gasConsu; // in l/100km
		this.prPerKm = this.getPrice()/2000 + prGas + this.gasConsu/100;
		this.picSource = "picture/scooterProjet.png";
	}
	
}
