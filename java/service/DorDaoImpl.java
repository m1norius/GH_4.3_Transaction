package service;

import dao.DorDao;
import entity.Dor;
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

public class DorDaoImpl implements DorDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }


    @Override
    @Transactional
    public void saveDor(Dor dor, int num) {

        final String color = dor.getColor();
        final int idCar = dor.getIdCar();
        final String sql = "INSERT INTO DOR(COLOR, ID_CAR) VALUES (?, ?)";


        KeyHolder dorKeyHolder = new GeneratedKeyHolder();

        for(int i = 0; i<num; i++){

            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
                    ps.setString(1, color);
                    ps.setInt(2, idCar);

                    return ps;
                }
            }, dorKeyHolder);
        }

        System.out.println("Saving done");
    }

    @Override
    public Dor readDor(int id) {
        String sql = "SELECT * FROM DOR WHERE ID = ?";
        final Dor dor = new Dor();
        jdbcTemplate.query(sql, new RowMapper<Dor>() {
            @Override
            public Dor mapRow(ResultSet rs, int rowNum) throws SQLException {
                dor.setColor(rs.getString("COLOR"));
                dor.setId(rs.getInt("ID"));
                dor.setIdCar(rs.getInt("ID_CAR"));
                return dor;
            }
        }, id);
        System.out.println("Reading done");
        return dor;
    }

    @Override
    public void updateDor(Dor dor, int id) {
        String color = dor.getColor();
        int idCar = dor.getIdCar();
        String sql = "UPDATE DOR SET COLOR = ?, ID_CAR = ? WHERE ID = ?";
        this.jdbcTemplate.update(sql, color, idCar, id);
        System.out.println("Updating done");
    }

    @Override
    public void deleteDor(int id) {
        String sql = "DELETE FROM DOR WHERE ID =?";
        this.jdbcTemplate.update(sql, id);
        System.out.println("Deleting done");
    }
}
