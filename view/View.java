package view;

import Modele.Company;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * @author boiredeleau
 *
 */
public class View {
	
	private Company company;
	private Controller controller;
	private Button addEmployee;
	private Button addVehicle;
	
	private Label dn;
	private Label pld;
	private Label speed;
	private Label price;
	private Label co2;
	
	/**
	 * Constructor of the main window, the map, the information of the first delivery 
	 * and two buttons to add some employee or vehicle.
	 * @param company 
	 * @throws Exception
	 */
	public View(Company company) throws Exception {
		this.company = company;
		this.controller = new Controller(company,this);
		
		
		// Button to add :
		
		addEmployee = new Button("add employee");
		addEmployee.setOnAction(event -> {
		 this.showAddEmployee();
		});
		
		addVehicle = new Button("add vehicle");	
		addVehicle.setOnAction(event -> {
			 this.showAddVehicle();
		});
		
		
		// Label with the information of the delivery.
		
		dn = new Label(printDriverName());
    	pld = new Label(printPayload());
    	speed = new Label(printSpeed());
    	price = new Label(printPrice());
    	co2 = new Label(printCo2());
		
		// Component of the main window.
    	
    	Stage stage = new Stage();
		
    	//group in which the background will be added.
		Group gpMap = new Group();
		//grid pane in which the label will be added.
    	GridPane gridInfo = new GridPane();    	
    	//border Pane which will divide the main scene.
    	BorderPane border = new BorderPane();
    	
    	Scene mainScene = new Scene(border,1000,600);
    	
    	//Background : 
    	
    	Picture backGround = new Picture("picture/cityMap.jpg");
    	Image city = new Image(backGround.getSourcePath());
		ImageView map = new ImageView(city);
		gpMap.getChildren().add(map);

		//This circle will represent the location of the company.
    	Circle cir = new Circle(this.company.getCompanyLocation().getX(),this.company.getCompanyLocation().getY(),7,Color.BLACK);
    	
    	gpMap.getChildren().add(cir);
    	//Add of a event handler that will handler what to do when the user click on the map.
    	gpMap.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg) {
				try {
					View.this.showPickMealWeight();
					View.this.controller.mouseClicked(arg.getX(), arg.getY(),gpMap);	
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    	});
    	
    	//formatting of the gridInfo.
    	gridInfo.add(dn,0,0);
    	gridInfo.add(pld,0,1);
    	gridInfo.add(speed,1,0);
    	gridInfo.add(price,1,1);
    	gridInfo.add(co2,0,2);
    	gridInfo.add(this.addEmployee,0,3);
    	gridInfo.add(this.addVehicle,1,3);
    	gridInfo.setHgap(15);
    	gridInfo.setVgap(45);
    	
    	border.setLeft(gpMap);
    	border.setCenter(gridInfo);
    	

    	stage.setScene(mainScene);
    	stage.show();
	}
	
	//Function to create a window to choose the weight of the meal when the user click on the map.
	public void showPickMealWeight() {
		Stage windowWeight = new Stage();
		BorderPane border  = new BorderPane();
    	Scene scene        = new Scene(border,350,50);
    	TextField mealWeight = new TextField("meal's weight in kg");
    	Button confirm  = new Button("Confirm");
    	mealWeight.setPrefColumnCount(15);
    	border.setCenter(mealWeight);
    	border.setBottom(confirm);
    	
    	//Add of the action the button will do when pressed.
    	confirm.setOnAction(action -> {
    		this.controller.pickMealWeight(mealWeight);
    		windowWeight.close();
    		this.controller.startMove();
    	});
    	windowWeight.setScene(scene);
    	windowWeight.show();
	}
	
	//Function to create a window to add a Employee
	public void showAddEmployee() {
    	Stage windowEmp   = new Stage();
    	BorderPane border = new BorderPane();
    	Scene scene       = new Scene(border,350,50);
    	SplitMenuButton splitMenuButton = new SplitMenuButton();
    	splitMenuButton.setText("what type of vehicle do you want to add ?");
    	Group g1 = new Group(splitMenuButton);
    	border.setTop(g1);
    	MenuItem cyclist = new MenuItem("Cyclist");
    	MenuItem driver  = new MenuItem("Driver");
    	splitMenuButton.getItems().addAll(cyclist, driver);
    	
    	//Add of the action the button will do when pressed.
    	cyclist.setOnAction((e)-> {
    	    this.showAddCyclist();
    	    windowEmp.close();
    	});
    	
    	//Add of the action the button will do when pressed.
    	driver.setOnAction((e)-> {
    	   this.showAddDriver();
    	   windowEmp.close();
    	   
    	});
    	
    	windowEmp.setScene(scene);
    	windowEmp.show();
    }
	
	//Function to create a window to add a Cyclist displayed after showAddEmployee().
	public void showAddCyclist() {
		Stage newWindow = new Stage();
    	newWindow.setTitle("add of a employee");
    	BorderPane groupBorder = new BorderPane();
    	Group groupText = new Group(groupBorder);	
    	BorderPane border = new BorderPane();
    	Button confirm  = new Button("Confirm");
    	
    	//Text field in which the user will give the information to the creation of the employee.
    	TextField empName   = new TextField("Enter the employee's name");
        TextField empSalary = new TextField("Enter the employee's salary");
        TextField empWeight = new TextField("Enter the employee's weight");
        TextField empLevel  = new TextField("Enter the employee's level, 1,2 or 3");
        
        //Set the size of the text field.
        empName.setPrefColumnCount(20);
        //Add of the text field in a verticale boxe.
        VBox vbText = new VBox(empName,empSalary,empWeight,empLevel);
        
        //then in a the border pane with the confirme button.
        groupBorder.setLeft(vbText);
        groupBorder.setBottom(confirm);
        
        Scene scene = new Scene(border,300,250);
        border.setCenter(groupText);
        
        //Add of the action the button will do when pressed.
        confirm.setOnAction(action -> {
        	this.controller.addCyclistToCompany(empName,empSalary,empWeight,empLevel);
        	newWindow.close();
        });

        newWindow.setScene(scene);
        newWindow.show();
	}
	
	//The same that the showAddCyclist() with different text field.
	public void showAddDriver() {
    	Stage newWindow   = new Stage();
    	newWindow.setTitle("add of a employee");
    	BorderPane groupBorder = new BorderPane();
    	Group groupText = new Group(groupBorder);  	
    	BorderPane border = new BorderPane();
    	Button confirm    = new Button("Confirm");
    	
    	//Text field in which the user will give the information to the creation of the employee.
    	TextField empName   = new TextField("Enter the employee's name");
        TextField empSalary = new TextField("Enter the employee's salary");
        TextField empWeight = new TextField("Enter the employee's weight");

        empName.setPrefColumnCount(15);
        VBox vbText = new VBox(empName,empSalary,empWeight);
        
        groupBorder.setLeft(vbText);
        groupBorder.setBottom(confirm);
        
        Scene scene = new Scene(border,300,250);
        border.setCenter(groupText);
        
        confirm.setOnAction(action -> {
        	this.controller.addDriverToCompany(empName, empSalary, empWeight);
        	newWindow.close();
        });

        newWindow.setScene(scene);
        newWindow.show();
    }
	
	//The same function that showAddEmployee() but for vehicle.
	public void showAddVehicle() {
    	Stage windowVec   = new Stage();
    	BorderPane border = new BorderPane();
    	Scene scene       = new Scene(border,350,50);
    	SplitMenuButton splitMenuButton = new SplitMenuButton();
    	splitMenuButton.setText("what type of vehicle do you want to add ?");
    	Group g1 = new Group(splitMenuButton);
    	border.setTop(g1);
    	MenuItem bike     = new MenuItem("Bike");
    	MenuItem scooter  = new MenuItem("scooter");


    	splitMenuButton.getItems().addAll(bike, scooter);
    	
    	bike.setOnAction((e)-> {
    	    this.showAddBike();
    	    windowVec.close();
    	});
    	scooter.setOnAction((e)-> {
    	   this.showAddScooter();
    	   windowVec.close();
    	});
    	
    	windowVec.setScene(scene);
    	windowVec.show();
    }
	
	//The same that the showAddCyclist() but for scooter.
	public void showAddScooter() {
    	Stage windowScooter = new Stage();
    	BorderPane border   = new BorderPane();
    	Scene scene         = new Scene(border,300,150);
    	BorderPane groupBorder = new BorderPane();
    	Group groupText = new Group(groupBorder);
    	Button confirm      = new Button("Confirm");
    	
    	TextField scooterName   = new TextField("Scooter's name");
        TextField scooterPrice  = new TextField("Scooter's price");
        TextField scooterPayloadCapa = new TextField("Scooter's payload capacity");
        TextField scooterCylinder    = new TextField("Scooter's cylinder");
        TextField scooterGasComsu    = new TextField("Scooter's gas comsuption");
        
        scooterName.setPrefColumnCount(15);
        VBox vbText = new VBox(scooterName,scooterPrice,scooterPayloadCapa,scooterCylinder,scooterGasComsu);
        
        groupBorder.setLeft(vbText);
        groupBorder.setBottom(confirm);
    	
        border.setCenter(groupText);
        
        confirm.setOnAction(action -> {
        	this.controller.addScooterToCompany(scooterName,scooterPrice,scooterPayloadCapa,scooterCylinder,scooterGasComsu);
        	windowScooter.close();
        });
        
        windowScooter.setScene(scene);
        windowScooter.show();
    }
	
	//The same that the showAddCyclist() but for bike.
	public void showAddBike() {
    	Stage windowBike = new Stage();
    	BorderPane border = new BorderPane();
    	Scene scene = new Scene(border,300,75);
    	BorderPane groupBorder = new BorderPane();
    	Group groupText = new Group(groupBorder);
    	Button confirm = new Button("Confirm");
    	
    	TextField bikeName  = new TextField("Bike's name");
        TextField bikePrice = new TextField("Bike's price");

        VBox vbText = new VBox(bikeName,bikePrice);
        
        groupBorder.setLeft(vbText);
        groupBorder.setBottom(confirm);
    	
        border.setCenter(groupText);
        
        confirm.setOnAction(action -> {
        	this.controller.addBikeToCompany(bikeName, bikePrice);
        	windowBike.close();
        });
        
    	windowBike.setScene(scene);
    	windowBike.show();
    }
	
	//Window shown when a exception is created during the add of a employee a vehicle or the selection of a weight for a meal. 
	public void showErrorWindow() {
		Stage errorWindow = new Stage();
		Label label = new Label("The entered informations aren't correcte please retry");
        Scene scene = new Scene(label, 350, 100);
        errorWindow.setScene(scene);
        errorWindow.show();
	}
	
	
	// Function to show the first delivery information and to refresh the label.
	// The refresh is done by just calling the function after the modification of the first element
	// of company's delivery list.
	
	private String printDriverName() {
		if(this.company.getListDelivery().size() > 0)
			return this.company.getListDelivery().get(0).getDeliMan().getName();
		
		return "Pas de livraison";
	}
	
	private String printPayload() {
		if(this.company.getListDelivery().size() > 0)
            return String.valueOf(this.company.getListDelivery().get(0).getLunch().getWeight()+1) + " kg";

        return "Pas de livraison";
        
	}
	
	private String printSpeed() {
		if(this.company.getListDelivery().size() > 0)
			return String.valueOf(this.company.getListDelivery().get(0).getTransport().getSpeed()) + " km/h";
	
		return "Pas de livraison";
	}
	
	private String printPrice() {
		if(this.company.getListDelivery().size() > 0)
			return String.valueOf((int) this.company.getListDelivery().get(0).getRunPrice()) + " €";
	
		return "Pas de livraison";
	}
	
	private String printCo2() {
		if(this.company.getListDelivery().size() > 0) {
			return String.valueOf((int)this.company.getListDelivery().get(0).getEmiCo2()/1000) + " kg/km";
		}	
			
		return "Pas de livraison";
	}
	
	public Company getCompany() {
        return company;
    }
	
	public Controller getController() {
		return this.controller;
	}

	public void refreshDn() {
		dn.setText(printDriverName());
	}

	public void refreshPld() {
		this.pld.setText(printPayload());
	}

	public void refreshSpeed() {
		this.price.setText(printSpeed());
	}

	public void refreshPrice() {
		this.speed.setText(printPrice());
	}
	
	public void refreshCo2() {
		this.co2.setText(printCo2());
	}
	
	public void refreshLabel() {
		refreshDn();
		refreshPld();
		refreshSpeed();
		refreshPrice();
		refreshCo2();
	}

}
