package Modele;

public class Bike extends Vehicle{
	private Cyclist user;

	/**
	 * Construtor of Bike.
	 * @param name  name of the bike.
	 * @param price  price of the bike.
	 * @param user   the employee who use the bike.
	 */
	public Bike(String name, double price) {
		super(name, price);

		this.prPerKm = price /3000; //prix par km = prix / 3000
		this.payloadCapa = 0; // un vélo a une charge utile nul;
		this.co2Emi = 0; // g/Km //ne changera pas au cour de la course 
		this.avgSpd = 0; // when the bike is ownerless its speed is 0.
		this.picSource = "picture/bikeProjet.png";
		//user is not initialized because the employee's does not owne the bike and therefore the bike exist without its user.
	}
	
	// Getter :
	
	public Cyclist getUser() {
		return this.user;
	}
	
	// Setter : 
	
	public void changeUser(Cyclist user) {
		this.user = user;
		this.avgSpd = this.user.getSpeed();
	}
	
	
	
}
