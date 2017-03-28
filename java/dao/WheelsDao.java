package dao;

import entity.Wheels;

public interface WheelsDao {

    void saveWheels(Wheels wheels, int num);
    Wheels readWheels(int id);
    void updateWheels(Wheels wheels, int id);
    void deleteWheels(int id);
}
