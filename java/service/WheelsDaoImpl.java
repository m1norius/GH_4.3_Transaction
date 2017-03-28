package service;

import dao.WheelsDao;
import entity.Wheels;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WheelsDaoImpl implements WheelsDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    @Transactional
    public void saveWheels(Wheels wheels, int num) {
        final int diameter = wheels.getDiameter();
        final String name = wheels.getName();
        final int idCar = wheels.getIdCar();

        final String sql = "INSERT INTO WHEELS(NAME, DIAMETER, ID_CAR) VALUES (?, ?, ?)";

        KeyHolder dorKeyHolder = new GeneratedKeyHolder();

        for(int i = 0; i<num; i++){

            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
                    ps.setString(1, name);
                    ps.setInt(2, diameter);
                    ps.setInt(3, idCar);

                    return ps;
                }
            }, dorKeyHolder);
        }

        System.out.println("Saving done");

    }

    @Override
    public Wheels readWheels(int id) {
        String sql = "SELECT * FROM WHEELS WHERE ID = ?";
        final Wheels wheels = new Wheels();

        this.jdbcTemplate.queryForObject(sql, new RowMapper<Wheels>() {
            @Override
            public Wheels mapRow(ResultSet rs, int rowNum) throws SQLException {
                wheels.setDiameter(rs.getInt("DIAMETER"));
                wheels.setName(rs.getString("NAME"));
                wheels.setIdCar(rs.getInt("ID_CAR"));
                wheels.setId(rs.getInt("ID"));
                return wheels;
            }
        }, id);
        System.out.println("Reading done");
        return wheels;

    }

    @Override
    public void updateWheels(Wheels wheels, int id) {
        int diameter = wheels.getDiameter();
        String name = wheels.getName();
        int idCar = wheels.getIdCar();

        String sql = "UPDATE WHEELS SET NAME = ?, DIAMETER = ?, ID_CAR = ? WHERE ID = ?";
        this.jdbcTemplate.update(sql, name, diameter, idCar, id);
        System.out.println("Updating done");

    }

    @Override
    public void deleteWheels(int id) {
        String sql = "DELETE FROM WHEELS WHERE ID = ?";
        this.jdbcTemplate.update(sql, id);
        System.out.println("Deleting done");
    }
}
