package Modele;

public class Beginner extends Cyclist{
    /**
     * Constructor of the beginner
     * @param name name of the cyclist
     * @param weight weight of the cyclist
     * @param salary salary of the cyclist
     * @param speed the cyclist's speed is 10 km/h
     */
    public Beginner(String name, double weight, double salary){
        super(name, weight, salary);
        this.speed = 10.0; // the beginner always go to 10km/h.
        this.level = 1;
    }

}