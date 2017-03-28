import dao.CarDao;
import dao.DorDao;
import dao.EngineDao;
import dao.WheelsDao;
import entity.Car;
import entity.Dor;
import entity.Engine;
import entity.Wheels;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        TableUtil tableUtil = (TableUtil) context.getBean("tableUtil");
//        reloadTable(tableUtil);

        CarDao carDao = (CarDao) context.getBean("carDaoImpl");
//        carDao.saveCar(createCar("Audi", "100"));
//        carDao.updateCar(createCar("Ford", "Sierra"), 1);
//        carDao.deleteCar(2);
//        carDao.readCar(1);

        DorDao dorDao = (DorDao) context.getBean("dorDaoImpl");
        dorDao.saveDor(createDor("Red", 5), 4);
//        Dor dorFromDb = dorDao.readDor(5);
//        dorDao.updateDor(createDor("White", 3), 7);
//        dorDao.deleteDor(4);

        WheelsDao wheelsDao = (WheelsDao) context.getBean("wheelsDaoImpl");
//        wheelsDao.saveWheels(createWheels("Nokian", 19, 1), 2);
//        Wheels wheelsFromDb = wheelsDao.readWheels(3);
//        wheelsDao.deleteWheels(2);
//        wheelsDao.updateWheels(createWheels("Nokiam", 16, 4), 1);
        EngineDao engineDao = (EngineDao) context.getBean("engineDaoImpl");

//        engineDao.saveEngine(createEngine(630, "4.2", "dizel", 8));
//        Engine engineFromDb =  engineDao.readEngine(1);
//        engineDao.updateEngine(createEngine(210, "1.9", "benzin" , 4), 1);
//        engineDao.deleteEngine(1);


    }

    public static Car createCar(String mark, String model){
        Car car = new Car();
        car.setMark(mark);
        car.setModel(model);
        return car;
    }
    
    public static Dor createDor(String color, int idCar){
        Dor dor = new Dor();
        dor.setColor(color);
        dor.setIdCar(idCar);
        return dor;
    }

    public static Wheels createWheels(String name, int diameter, int idCar){
        Wheels wheels = new Wheels();
        wheels.setDiameter(diameter);
        wheels.setName(name);
        wheels.setIdCar(idCar);
        return wheels;
    }

    public static Engine createEngine(int power, String capacity, String type, int idCar){

        Engine engine = new Engine();
        engine.setPower(power);
        engine.setCapacity(capacity);
        engine.setType(type);
        engine.setIdCar(idCar);
        return engine;
    }

    public static void reloadTable(TableUtil tableUtil){
        tableUtil.dropTable();
        tableUtil.createTable();
    }














    //        "SELECT SALARY FROM POST, EMPLOYEE WHERE "
//                + "POST.ID_PROFESSION = EMPLOYEE.ID_PROFESSION "
//                + "AND "
//                + "POST.Profession = 'Tester' ");
}
