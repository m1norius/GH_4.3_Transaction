package entity;

/**
 * Created by minorius on 16.02.2017.
 */
public class Wheels {
    private String name;
    private int diameter;
    private int id;
    private int idCar;

    public int getId() {
        return id;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
}
