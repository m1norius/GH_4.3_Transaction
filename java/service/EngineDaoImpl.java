package service;

import dao.EngineDao;
import entity.Engine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EngineDaoImpl implements EngineDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }


    @Override
    public void saveEngine(Engine engine) {
        final int power = engine.getPower();
        final String capacity = engine.getCapacity();
        final String type = engine.getType();
        final int idCar = engine.getIdCar();

        final String sql = "INSERT INTO ENGINE(POWER, CAPACITY, TYPE, ID_CAR) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
                ps.setInt(1, power);
                ps.setString(2, capacity);
                ps.setString(3, type);
                ps.setInt(4, idCar);

                return ps;
            }
        }, keyHolder);

        System.out.println("Creating done");

    }

    @Override
    public Engine readEngine(int id) {
        String sql = "SELECT * FROM ENGINE WHERE ID = ?";
        final Engine engine = new Engine();

        this.jdbcTemplate.queryForObject(sql, new RowMapper<Engine>() {
            @Override
            public Engine mapRow(ResultSet rs, int rowNum) throws SQLException {
                engine.setPower(rs.getInt("POWER"));
                engine.setId(rs.getInt("ID"));
                engine.setCapacity(rs.getString("CAPACITY"));
                engine.setIdCar(rs.getInt("ID_CAR"));
                engine.setType(rs.getString("TYPE"));
                return engine;
            }
        }, id);
        System.out.println("Reading done");
        return engine;
    }

    @Override
    public void updateEngine(Engine engine, int id) {
        int idCar = engine.getIdCar();
        String type = engine.getType();
        String capacity = engine.getCapacity();
        int power = engine.getPower();

        String sql = "UPDATE ENGINE SET POWER = ?, CAPACITY = ?, TYPE = ?, ID_CAR = ? WHERE ID =?";

        this.jdbcTemplate.update(sql, power, capacity, type, idCar, id);
        System.out.println("Updating done");

    }

    @Override
    public void deleteEngine(int id) {
        String sql = "DELETE FROM ENGINE WHERE ID =?";
        this.jdbcTemplate.update(sql, id);
        System.out.println("Deleting done");

    }
}
