package Modele;

public class Athletic extends Cyclist{

    /**
     * Constructor
     * @param name name of the cyclist
     * @param weight weight of the cyclist
     * @param salary salary of the cyclist
     * @param speed the cyclist's speed is 15.0 km/h
     */
    public Athletic(String name, double weight, double salary){
        super(name, weight, salary);
        this.speed = 15.0;
        this.level = 2;
    }

}