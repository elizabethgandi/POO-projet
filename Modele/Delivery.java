package Modele;

public class Delivery {
	private Vehicle transport;
	private Employee DeliMan;
	private Meal lunch;
	private double RunPrice;
	private double EmiCo2;
	private double runTime;

	/**
	 * Constructor of Delivery.
	 * @param transport Vehicle use during the delivery.
	 * @param DeliMan   Employee in charge of the delivery.
	 * @param lunch     Meal deliver.
	 */
	public Delivery(Vehicle transport, Employee DeliMan, Meal lunch) {
		this.transport = transport;
		this.DeliMan = DeliMan;
		this.lunch = lunch;
		this.EmiCo2 = this.transport.getCo2Emi()*this.lunch.getdistance();
		this.runTime = this.lunch.getdistance()/this.transport.getSpeed();
		this.RunPrice = this.transport.prPerKm * this.lunch.getdistance() + this.DeliMan.getSalary()*this.runTime;
	}
	
	/**
	 * The function check is the delivery is possible.
	 * @return boolean result of the test.
	 */
	public boolean isDeliPossible(){
		double pld = Math.max(this.transport.getPayloadCapa(),this.DeliMan.getPayload());
		return pld >= lunch.getWeight() && this.runTime < 1;
	}

	/**
	 * The function compared two delivery and report if the delivery "this" is better or not.
	 * @param otherDeli The delivery wich will be compare to.
	 * @return boolean result of the test.
	 * @throws CloneNotSupportedException
	 */
	public boolean isBetter(Delivery otherDeli) throws CloneNotSupportedException {
			return this.EmiCo2 > otherDeli.getEmiCo2() || this.RunPrice > otherDeli.getRunPrice();
	}

	// Getter : 
	
	public Vehicle getTransport() {
		Vehicle vec = this.transport;
		return vec;
	}

	public Employee getDeliMan() {
		Employee emp = this.DeliMan;
		return emp;
	}

	public Meal getLunch() {
		Meal lch = this.lunch;
		return lch;
	}

	public double getRunPrice() {
		double rprc = this.RunPrice;
		return rprc;
	}

	public double getEmiCo2(){
		double co2 = this.EmiCo2;
		return co2;
	}

	public double getRunTime(){
		double time = this.runTime;
		return time;
	}

	// Override class Objet' functions : 
	
	@Override
	public Delivery clone() throws CloneNotSupportedException {
		return new Delivery(this.transport,this.DeliMan,this.lunch);
	}
	
	@Override
	public String toString() {
		return this.DeliMan.getName() + " run with " + this.lunch.getWeight() + "kg on a " + this.transport.getName();
	}
		
}