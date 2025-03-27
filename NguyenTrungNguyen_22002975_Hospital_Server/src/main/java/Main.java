import dao.DoctorDAO;
import util.AppUtil;

public class Main {
    public static void main(String[] args) {
        DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
//        System.out.println(doctorDAO.getNoOfDoctorsBySpeciality("Cardiology"));
        System.out.println(doctorDAO.listDoctorsBySpeciality("Bariatric"));
//        System.out.println(doctorDAO.updateDiagnosis("PT004", "DR.010", "COVID-19"));
    }
}
