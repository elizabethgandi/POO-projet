package application;

import Modele.*;
import view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
    	Point cmpnLctn = new Point(300,300); //company localisation
        Company company = new Company(cmpnLctn);
        
        company.addEmployee(new Driver("Aldfred",80.0,7.2));
        company.addEmployee(new Driver("Tiago",62,7.25));
        company.addEmployee(new Beginner("Jade",55.0,7.1));
        company.addEmployee(new Expert("Anna",67,7.4));
        company.addEmployee(new Athletic("Blaise",74,7.25));
        
        company.addVehicle(new Scooter("Yamama",3500,50,300,7.5));
        company.addVehicle(new Scooter("Vespo",2500,30,125,5.5));
        company.addVehicle(new Scooter("Piagigi",2000,35,150,6));
        company.addVehicle(new Bike("Cyclou",450));
        company.addVehicle(new Bike("Vavite",500));

        View v = new View(company);     
    }
}





