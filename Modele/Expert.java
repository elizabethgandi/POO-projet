package Modele;

public class Expert extends Cyclist{
    /**
     * Constructor of the expert cyclist.
     * @param name name of the cyclist
     * @param weight weight of the cyclist
     * @param salary salary of the cyclist
     * @param speed the cyclist's speed is 20.0 km/h
     */
    public Expert (String name, double weight, double salary){
        super(name, weight, salary);
        this.speed = 20.0; // the expert always go to 20km/h.
        this.level = 3;
    }

}