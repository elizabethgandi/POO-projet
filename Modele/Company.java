package Modele;

import java.util.*;

public class Company {

	//declaration of attributs -------------------------------------------------------------

	private ArrayList<Vehicle> listVehicle;   //list of listed vehicles
	private ArrayList<Employee> listEmployee; //list of listed employees
	private ArrayList<Delivery> listDelivery; //list of listed deliveries
	
	/*
	 creation of 2 lists to prevent the user to not using twice an
	 employee or an vehicle
	*/
	private ArrayList<Employee> listBusyEmployee;
	private ArrayList<Vehicle> listBusyVehicle;
	Point companyLocation;

	
	/**
	 * Constructor wich initializes all the list to empty list: 
	 * @param companyLocation is the GPS coordinates of the company
	 */
	public Company(Point companyLocation) {
		this.listVehicle  = new ArrayList<Vehicle>();
		this.listEmployee = new ArrayList<Employee>();
		this.listDelivery = new ArrayList<Delivery>();
		this.listBusyEmployee = new ArrayList<Employee>();
		this.listBusyVehicle  = new ArrayList<Vehicle>();
		this.companyLocation = companyLocation;
	}
	
	/**
	 * constructor wich initializes the company with non empty list:
	 * @param listVehicle the list of the vehicle
	 * @param listEmployee the list of the employees
	 */

	public Company(ArrayList<Vehicle> listVehicle, ArrayList<Employee> listEmployee,Point companyLocation) {
		this.listVehicle = listVehicle;
		this.listEmployee = listEmployee;
		this.listDelivery = new ArrayList<Delivery>();     //list of deliveries in progess
		this.listBusyEmployee = new ArrayList<Employee>(); //list of employees which are delivering
		this.listBusyVehicle  = new ArrayList<Vehicle>();  // list of vehicles wich are used to delivering
		this.companyLocation = companyLocation;
	}

	/**
	 * Method which return the distance of the delivery location to the company, we choose that one pixel = 58m = 0.058 km.
	 * Wich mean that 35km = 550pixel.
	 * @param deliveryLocation is the place where the company have to deliver the meal
	 * @return the distance beteween the delivery location and the place's company. It will use after for know which run is the best delivery
	 */

	public double disantToCompany(Point deliveryLocation){ 
		int horizontalDif = Math.abs(this.companyLocation.getX() - deliveryLocation.getX()); //number of pixels
		int verticalDif   = Math.abs(this.companyLocation.getY() - deliveryLocation.getY()); //number of pixels
		double diagoDif   = Math.sqrt(Math.pow((double) horizontalDif, 2) + Math.pow((double) verticalDif, 2)); //diagonal difference of pixel.
		
		return diagoDif*35/550;

	}
	
	/**
	 * Method call when the Map is clicked, it determined the best delivery
	 * and add it to the company's list of delivery.
	 * The function make every possible combination of delivery then put them in a list and 
	 * put the best one in first position.
	 * @param distance distance between the company's building and the delivery location in km.
	 * @throws CloneNotSupportedException
	 * @return a boolean for know if it's the best delivery or not
	 */
	public boolean bestDelivery(Meal lunch) throws CloneNotSupportedException {
		ArrayList<Delivery> combiDeli = new ArrayList<Delivery>();
		Delivery deliTmp;
		for(Vehicle vec : this.listVehicle){
			for(Employee emp : this.listEmployee){
				if(this.isDuoVecEmpOk(vec,emp)) {
					if(this.isDuoCyclistBikeOk(vec, emp)) 
						((Bike)vec).changeUser((Cyclist)emp);						
					deliTmp = new Delivery(vec,emp,lunch);
					if(deliTmp.isDeliPossible())
						combiDeli.add(deliTmp);
				}
			}
		}
		if(combiDeli.size() > 0){
			for(int i=0;i<combiDeli.size();i++){
				if(combiDeli.get(i).isBetter(combiDeli.get(0)))
					combiDeli.set(0,combiDeli.get(i));
			}
			this.addDelivery(combiDeli.get(0));
			return true;
		}
		System.out.println("impossible delivery");
		return false;
	
	}

	/**
	 * Method which check if the employee can use the vehicle or not
	 * @param vec the vehicle that the employee must use to move
	 * @param emp the employee chooses
	 * @return is the employee use a bike or a scooter always 
	 */
	public boolean isDuoVecEmpOk(Vehicle vec,Employee emp) {
		return isDuoCyclistBikeOk(vec,emp) || isDuoDriverScootereOk(vec,emp);
	}
	
	/**
	 * Method that checks if the employee is a cyclist and if the vehicle is a bike
	 * @param vec the vehicle
	 * @param emp the employee
	 * @return true if the employee is a cyclist and his level, and false if the employee is a driver
	 */
	public boolean isDuoCyclistBikeOk(Vehicle vec, Employee emp) {
		boolean bikeBeginner = vec.getClass() == Bike.class && emp.getClass() == Beginner.class;
		boolean bikeAthletic = vec.getClass() == Bike.class && emp.getClass() == Athletic.class; 
		boolean bikeExpert = vec.getClass() == Bike.class && emp.getClass() == Expert.class;
		return bikeBeginner || bikeAthletic || bikeExpert;
	}
	
	/**
	 * Method that checks if the employee is a driver and if the vehicle is a scooter
	 * @param vec the vehicle 
	 * @param emp the employee
	 * @return true if the employee is a driver and false if the employee is a cyclist
	 */
	public boolean isDuoDriverScootereOk(Vehicle vec, Employee emp) {
		return vec.getClass() == Scooter.class && emp.getClass() == Driver.class;
	}

	//Method of list gestion :  -------------------------------------------------------------
	
	/**
	 * Method that adds a vehicle to the list of vehicles available for make a race
	 * @param vec the vehicle use
	 */

	public void addVehicle(Vehicle vec){
		this.listVehicle.add(vec);
	}

	/**
	 * Method that deletet a vehicle to the list of vehicles available for make a race
	 * @param vec the vehicle use
	 */

	public void removeVehicle(Vehicle vec){
		this.listVehicle.remove(vec);
	}

	/**
	 * Method that adds an employee to the list of employees available for make a race
	 * @param emp the employee free
	 */

	public void addEmployee(Employee emp){
		this.listEmployee.add(emp);
	}

	/**
	 * Method that delete an employee to the list of employees available for make a race
	 * @param emp the employee free
	 */
	public void removeEmployee(Employee emp){
		this.listEmployee.remove(emp);
	}

	/**
	 * Method that adds a delivery to the list of delivery to do
	 * @param lch ordered meal that must be delivered 
	 */

	public void addDelivery(Delivery lch){
		this.listDelivery.add(lch);
	}

	/**
	 * Method that delete a delivery to the list of delivery to do
	 * @param lch meal that was delivered
	 */

	public void removeDelivery(Delivery lch){
		this.listDelivery.remove(lch);
	}

	/**
	 * Method that adds a vehicle to the list of busy vehicles, vehicles 
	 * who are already traveling so not available
	 * @param vec vehicle busy
	 */

	private void addVehicleBusy(Vehicle vec){
		this.listBusyVehicle.add(vec);
	}

	/**
	 * Method that delete a vehicle to the list of busy vehicles because it 
	 * finish it delivery so it is now free
	 * @param vec vehicle free
	 */

	public void removeVehicleBusy(Vehicle vec){
		this.listBusyVehicle.remove(vec);
	}

	/**
	 * Method that adds an employee to the list of busy employees, employees
	 * who are already traveling so not available
	 * @param emp employee
	 */

	public void addEmployeeBusy(Employee emp){
		this.listBusyEmployee.add(emp);
	}

	/**
	 * Method that delete an employee to the list of busy employees because he 
	 * finish his delivery so he is now free
	 * @param emp employee free
	 */

	public void removeEmployeeBusy(Employee emp){
		this.listBusyEmployee.remove(emp);
	}
	
	/**
	 * Method which moves a free vehicle to the list of busy vehicles
	 * @param vec vehicle wich became unvailaible
	 */

	public void moveVecToBusy(Vehicle vec) {
		this.removeVehicle(vec);
		this.addVehicleBusy(vec);
	}
	
	/**
	 * Method which moves a free employee to the list of busy employees
	 * @param emp employee which became unvailable
	 */

	public void moveEmpToBusy(Employee emp) {
		this.removeEmployee(emp);
		this.addEmployeeBusy(emp);
	}
	
	/**
	 * Method which moves a unvailable vehicle to the list of free vehicles
	 * @param vec vehicle now free
	 */

	public void moveVecToFree(Vehicle vec) {
		this.removeVehicleBusy(vec);
		this.addVehicle(vec);
	}
	
	/**
	 *  Method which moves a unvailable employee to the list of free employee
	 * @param emp employee now free
	 */
	public void moveEmpToFree(Employee emp) {
		this.removeEmployeeBusy(emp);
		this.addEmployee(emp);
	}
	
	/**
	 * Method that moves a vehicle and a free employee to the list of vehicle 
	 * not available and employee not available
	 * @param vec vehicle wich became unvailaible
	 * @param emp employee wich became unvailaible
	 */
	public void moveToBusyVecEmp(Vehicle vec,Employee emp) {
		moveVecToBusy(vec); 
		moveEmpToBusy(emp);
	}
	
	/**
	 * Method that moves a vehicle and an unvailable employee to the list of vehicle 
	 * free and employee free
	 * @param vec vehicle wich became free
	 * @param emp employee wich became free
	 */
	public void moveToFreeVecEmp(Vehicle vec,Employee emp) {
		moveVecToFree(vec); 
		moveEmpToFree(emp);
	}
	
	//end of the list gestion methode -------------------------------------------------------------
	
	/**
	 * Method that returns the list of deliveries
	 * @return list of deliveries
	 */
	public ArrayList<Delivery> getListDelivery() {
		return (ArrayList<Delivery>) listDelivery.clone();
	}
	
	/**
	 * Method that returns the list of vehicles
	 * @return list of vehicles
	 */
	public ArrayList<Vehicle> getListVehicle() {
		return (ArrayList<Vehicle>) listVehicle.clone();
	}

	/**
	 * Method that returns the list of employees
	 * @return list of employees
	 */
	public ArrayList<Employee> getListEmployee() {
		return (ArrayList<Employee>) listEmployee.clone();
	}
	

	/**
	 * Method that returns the company's gps coordinates
	 * @return company's gps coordinates
	 */
	public Point getCompanyLocation(){
		Point cmpLoca = this.companyLocation;
		return cmpLoca;
	}
}