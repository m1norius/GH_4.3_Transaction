package service;

import dao.CarDao;
import entity.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public void saveCar(Car car) {

        final String mark = car.getMark();
        final String model = car.getModel();

        final String sql = "INSERT INTO CAR(MARK, MODEL) VALUES (?, ?)";

        KeyHolder carKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
                ps.setString(1, mark);
                ps.setString(2, model);

                return ps;
            }
        }, carKeyHolder);

        System.out.println("Creating complete");

    }

    @Override
    public Car readCar(int id) {

        final Car car = new Car();
        final String sql = "SELECT MARK, MODEL, ID FROM CAR WHERE ID = ?";

        this.jdbcTemplate.queryForObject(sql, new RowMapper<Car>() {
            @Override
            public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
                car.setMark(rs.getString("MARK"));
                car.setModel(rs.getString("MODEL"));
                car.setId(rs.getInt("ID"));
                return car;
            }
        }, id);
        System.out.println("Reading complete");
        return car;
    }

    @Override
    public void updateCar(Car car, int id) {
        final String mark = car.getMark();
        final String model = car.getModel();
        String sql = "UPDATE CAR SET MARK = ?, MODEL = ? WHERE ID = ?";

        this.jdbcTemplate.update(sql, mark, model, id);
        System.out.println("Updating complete");
    }

    @Override
    public void deleteCar(int id) {

        String sql = "DELETE FROM CAR WHERE ID = ?";
        this.jdbcTemplate.update(sql, id);
        System.out.println("Deleting complete");

    }
}
