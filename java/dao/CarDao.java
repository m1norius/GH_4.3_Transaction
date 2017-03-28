package dao;

import entity.Car;

public interface CarDao {

    void saveCar(Car car);
    Car readCar(int id);
    void updateCar(Car car, int id);
    void deleteCar(int id);

}
