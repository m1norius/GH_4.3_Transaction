package dao;

import entity.Dor;

public interface DorDao {

    void saveDor(Dor dor, int num);
    Dor readDor(int id);
    void updateDor(Dor dor, int id);
    void deleteDor(int id);
}
