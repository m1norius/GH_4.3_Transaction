import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class TableUtil {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Transactional
    public void createTable() {
        try {
            this.jdbcTemplate.execute("CREATE TABLE PUBLIC.ENGINE (id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, power INTEGER(150) NOT NULL, capacity FLOAT(150) NOT NULL, TYPE VARCHAR(150) NOT NULL, id_car INT(150) NOT NULL)");
            this.jdbcTemplate.execute("CREATE TABLE PUBLIC.WHEELS (id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, name VARCHAR(150) NOT NULL, diameter INTEGER NOT NULL, id_car INT(150) NOT NULL)");
            this.jdbcTemplate.execute("CREATE TABLE PUBLIC.CAR (id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, mark VARCHAR(150) NOT NULL, model VARCHAR(150) NOT NULL)");
            this.jdbcTemplate.execute("CREATE TABLE PUBLIC.DOR(id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, color VARCHAR(150) NOT NULL , id_car INT(150) NOT NULL)");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Transactional
    public void dropTable() {
        try {
            this.jdbcTemplate.execute("DROP TABLE PUBLIC.ENGINE");
            this.jdbcTemplate.execute("DROP TABLE PUBLIC.WHEELS");
            this.jdbcTemplate.execute("DROP TABLE PUBLIC.CAR");
            this.jdbcTemplate.execute("DROP TABLE PUBLIC.DOR");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
