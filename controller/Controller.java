package controller;

import view.*;
import Modele.Athletic;
import Modele.Beginner;
import Modele.Bike;
import Modele.Company;
import Modele.Delivery;
import Modele.Driver;
import Modele.Employee;
import Modele.Expert;
import Modele.Meal;
import Modele.Point;
import Modele.Scooter;
import Modele.Vehicle;
import javafx.scene.Group;
import javafx.scene.control.TextField;

public class Controller {

    private Company company;
    private View view;
    private double weight;
    private MoveAnim move;
    
    /**
     * Contructor of the controller
     * @param company
     * @param view
     */
    public Controller(Company company, View view){
        this.company = company;       
        this.view = view;
    }

    /**
     * Fonction that will call all the fonction to make the animation and in case the delivery
     * is not possible not start it.
     * @param x
     * @param y
     * @param group
     * @throws Exception
     */
    public void mouseClicked(double x,double y,Group group) throws Exception {
    	Meal lunch = new Meal(this.weight,this.company.disantToCompany(new Point((int)x,(int)y)));
    	boolean goAnim = this.company.bestDelivery(lunch);
    	if(goAnim){
    		int deliNum = this.company.getListDelivery().size()-1;//delivery's number always the last one because the other are already created.
        	this.move = new MoveAnim(new Point((int) x,(int) y),this.company.getListDelivery().get(deliNum),this.company,this.view);
        	group.getChildren().add(move.getPicture());
    	}
    }
    
    /**
     * This function will assigned the weight of the meal to the attribute 
     * of the class.
     * @param mealWeight
     */
    public void pickMealWeight(TextField mealWeight) {
    	try {
    		double weight = Double.parseDouble(mealWeight.getText()); // test if the text in convertible to double
    		this.setWeight(weight);
    	} catch(Exception e) {
    		this.view.showErrorWindow();
    	}
    }
    
    /**
     * This function will start the animation
     */
    public  void startMove() {
    	this.move.starMove();
    }
    
    /**
     * This function will call a function of the company to 
     * report that this vehicle and employee are on a delivery.
     * @param vec vehicle to move to the other list.
     * @param emp employee to move to the other list.
     */
    public void moveVecEmpToBusy(Vehicle vec, Employee emp) {
    	this.view.getCompany().moveToBusyVecEmp(vec,emp);
    }
    
    /**
     * This function will call a function of the company to 
     * report that this vehicle and employee are now free.
     * @param vec vehicle to move to the other list.
     * @param emp employee to move to the other list.
     */
    public void moveVecEmpToFree(Vehicle vec, Employee emp) {
    	this.view.getController().getCompany().moveToFreeVecEmp(vec,emp);
    }  
    
    /**
     * This function will add a cyclist to the company after its information are given by the user.
     * @param empName   text field from where the name will be take
     * @param empSalary text field from where the salary will be take.
     * @param empWeight text field from where the weight will be take
     * @param empLevel  text field from where the level will be take.
     */
    public void addCyclistToCompany(TextField empName, TextField empSalary, TextField empWeight,TextField empLevel) {
    	try {
    		String name   = empName.getText();
    		double salary = Double.parseDouble(empSalary.getText());
    		double weight = Double.parseDouble(empWeight.getText());
    		int level     = Integer.parseInt(empLevel.getText());
    		boolean levelOk = isLevelOk(level);
    		
    		switch(level) {
			case 1 :  
		    	this.company.addEmployee(new Beginner(name,salary,weight));
				break;
			case 2 :
				this.company.addEmployee(new Athletic(name,salary,weight));
				break;
			case 3 :
				this.company.addEmployee(new Expert(name,salary,weight));
				break;
    		}
    	} catch(Exception e) {
    		this.view.showErrorWindow();
    	}
    }
   /**
    * This function will add a driver to the company after its information are given by the user.
    * @param empName   text field from where the name will be take
    * @param empSalary text field from where the salary will be take.
    * @param empWeight text field from where the weight will be take
    */
    public void addDriverToCompany(TextField empName,TextField empSalary,TextField empWeight) {
    	try {
    		String name   = empName.getText();
    		double salary = Double.parseDouble(empSalary.getText());
    		double weight = Double.parseDouble(empWeight.getText());
    		this.company.addEmployee(new Driver(name,salary,weight));
    	} catch(Exception e) {
    		this.view.showErrorWindow();
    	}
    }

    /**
     * This function will add a scooter to the company after its information are given by the user.
     * @param scooterName         text field wich give the scooter's name. 
     * @param scooterPrice        text field wich give the scooter's price.
     * @param scooterPayloadCapa  text field wich give the scooter's payload capacity.
     * @param scooterCylinder     text field wich give the scooter's cylinder.
     * @param scooterGasComsu     text field wich give the scooter's gas comsuption.
     */
    public void addScooterToCompany(TextField scooterName ,TextField scooterPrice,TextField scooterPayloadCapa,TextField scooterCylinder,TextField scooterGasComsu ) {
    	try {
    		String name = scooterName.getText();
    		double price = Double.parseDouble(scooterPrice.getText());
    		double payloadCapa = Double.parseDouble(scooterPayloadCapa.getText());
    		double cylinder = Double.parseDouble(scooterCylinder.getText());
    		double GasComsu = Double.parseDouble(scooterGasComsu.getText());
    		this.company.addVehicle(new Scooter(name,price,payloadCapa,cylinder,GasComsu));
    	} catch(Exception e) {
    		this.view.showErrorWindow();
    	}
    }
    
    /**
     * This function will add a bike to the company after its information are given by the user.
     * @param bikeName   text field wich give the bike's name.
     * @param bikePrice  text field wich give the bike's price.
     */
    public void addBikeToCompany(TextField bikeName,TextField bikePrice) {
    	try {
    		String name = bikeName.getText();
    		double price = Double.parseDouble(bikePrice.getText());
    		this.company.addVehicle(new Bike(name,price));
    	} catch(Exception e) {
    		this.view.showErrorWindow();
    	}
    	
    }
    
    /**
     * This function test if the level of a cyclist is between 1 & 3.
     * @param level      integer variable wich will be tested.
     * @return boolean   result of the test.
     * @throws Exception if the test is false then the information has been wrongly given.
     */
    private boolean isLevelOk(int level) throws Exception {
    	if(!(level >= 1 && level <= 3))
    		throw new Exception();
    	
    	return true;	
    }
 
    /**
     * Getter on the attribut company.
     * @return the attribut company.
     */
    public Company getCompany() {
    	return this.company;
    }
    
    /**
     * Setter on the attibut weight.
     * @param weight double to wich the attribut will be change.
     */
    public void setWeight(double weight) {
    	this.weight = weight;
    }
   
}
