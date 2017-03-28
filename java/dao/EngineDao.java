package dao;

import entity.Engine;

public interface EngineDao {

    void saveEngine(Engine engine);
    Engine readEngine(int id);
    void updateEngine(Engine engine, int id);
    void deleteEngine(int id);
}
