package entity;

/**
 * Created by minorius on 16.02.2017.
 */
public class Dor {

    private String color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
