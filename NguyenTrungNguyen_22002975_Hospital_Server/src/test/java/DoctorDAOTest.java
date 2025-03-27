import dao.DoctorDAO;
import model.Doctor;
import org.junit.jupiter.api.Test;
import util.AppUtil;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorDAOTest {
    @Test
    public void testAddDoctor() {
        Doctor doctor = new Doctor("DR.099", "Nguyen Trung Nguyen", "0123456789", "Cardiology");
        DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
        assertTrue(doctorDAO.addDoctor(doctor));
    }

    @Test
    public void testGetNoOfDoctorsBySpeciality() {
        DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
        Map<String, Long> result = doctorDAO.getNoOfDoctorsBySpeciality("Cardiology");
        assertEquals(2, result.size());
        assertEquals(1, result.get("Cardiology"));
    }

    @Test
    public void testListDoctorsBySpeciality() {
        DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
        List<Doctor> result = doctorDAO.listDoctorsBySpeciality("Bariatric");
        assertEquals(2, result.size());
        assertEquals("DR.026", result.get(0).getId());
        assertEquals("DR.033", result.get(1).getId());
    }

    @Test
    public void testUpdateDiagnosis() {
        DoctorDAO doctorDAO = new DoctorDAO(AppUtil.getDriver(), "nguyen22002975");
        assertTrue(doctorDAO.updateDiagnosis("PT004", "DR.010", "COVID-19"));
    }
}
