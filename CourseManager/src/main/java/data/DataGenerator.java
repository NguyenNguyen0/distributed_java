package data;

import Util.JPAUtil;
import model.*;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.*;

public class DataGenerator {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public Address generateAddress() {
        Address address = new Address();
        address.setStreet(faker.address().streetAddress() + " " + faker.address().buildingNumber());
        address.setCity(faker.address().city());
        address.setState(faker.address().state());
        address.setPostalCode(Integer.parseInt(faker.address().zipCode()));
        return address;
    }

    public Clazz generateClazz(ClazzProfile clazzProfile) {
        Clazz clazz = new Clazz();
        clazz.setId(faker.number().digits(5));
        clazz.setName(faker.educator().course());
        clazz.setNoOfStudents(faker.number().numberBetween(10, 30));
        clazz.setProfile(clazzProfile);
        return clazz;
    }

    public ClazzProfile generateClazzProfile() {
        ClazzProfile profile = new ClazzProfile();
        profile.setId(faker.lorem().sentence());
        profile.setDescription(faker.address().city());
        profile.setCreateDate(LocalDate.of(
                random.nextInt(20) + 2000,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1
        ));
        return profile;
    }

    public Course generateCourse() {
        Course course = new Course();
        course.setId(faker.number().digits(5));
        course.setName(faker.educator().course());
        course.setCredits(faker.number().numberBetween(1, 5));
        return course;
    }

    public FullTimeStudent generateFullTimeStudent(Clazz clazz) {
        FullTimeStudent student = new FullTimeStudent();
        student.setId(faker.number().digits(5));
        student.setName(faker.name().fullName());
        student.setEmail(faker.internet().emailAddress());
        student.setGender(Gender.values()[random.nextInt(3)]);
        student.setAddress(generateAddress());
        student.setPhones(new HashSet<>(List.of(faker.phoneNumber().phoneNumber())));
        student.setDob(LocalDate.of(
                random.nextInt(20) + 1980,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1
        ));
        student.setClazz(clazz);
        student.setFaculty(faker.educator().university());
        student.setDepartment(faker.educator().course());
        return student;
    }

    public PartTimeStudent generatePartTimeStudent(Clazz clazz) {
        PartTimeStudent student = new PartTimeStudent();
        student.setId(faker.number().digits(5));
        student.setName(faker.name().fullName());
        student.setEmail(faker.internet().emailAddress());
        student.setGender(Gender.values()[random.nextInt(3)]);
        student.setAddress(generateAddress());
        student.setPhones(new HashSet<>(List.of(faker.phoneNumber().phoneNumber())));
        student.setDob(LocalDate.of(
                random.nextInt(20) + 1980,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1
        ));
        student.setClazz(clazz);
        student.setSupervisor(faker.name().fullName());
        return student;
    }

    public Enrollment generateEnrollment(Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setSemester("Spring");
        enrollment.setYear(2021);
        enrollment.setScore(faker.number().numberBetween(0, 100));
        return enrollment;
    }

    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();

        HashSet<ClazzProfile> clazzProfiles = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            clazzProfiles.add(dataGenerator.generateClazzProfile());
        }
        HashSet<Clazz> clazzes = new HashSet<>();
        for (ClazzProfile profile : clazzProfiles) {
            clazzes.add(dataGenerator.generateClazz(profile));
        }

        HashSet<Course> courses = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            courses.add(dataGenerator.generateCourse());
        }

        ArrayList<ClazzProfile> clazzProfileList = new ArrayList<>(clazzProfiles);
        HashSet<FullTimeStudent> fullTimeStudents = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            fullTimeStudents.add(
                    dataGenerator.generateFullTimeStudent(clazzProfileList.get(random.nextInt(clazzProfileList.size())).getClazz())
            );
        }

        HashSet<PartTimeStudent> partTimeStudents = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            partTimeStudents.add(
                    dataGenerator.generatePartTimeStudent(clazzProfileList.get(random.nextInt(clazzProfileList.size())).getClazz())
            );
        }

        ArrayList<Course> courseList = new ArrayList<>(courses);
        HashSet<Enrollment> enrollments = new HashSet<>();
        for (FullTimeStudent student : fullTimeStudents) {
            enrollments.add(
                    dataGenerator.generateEnrollment(student, courseList.get(random.nextInt(courseList.size())))
            );
        }

        for (PartTimeStudent student : partTimeStudents) {
            enrollments.add(
                    dataGenerator.generateEnrollment(student, courseList.get(random.nextInt(courseList.size())))
            );
        }

        JPAUtil.getEntityManager("mariadb").getTransaction().begin();
        clazzes.forEach(JPAUtil.getEntityManager("mariadb")::persist);
        clazzProfiles.forEach(JPAUtil.getEntityManager("mariadb")::persist);
        courses.forEach(JPAUtil.getEntityManager("mariadb")::persist);
        fullTimeStudents.forEach(JPAUtil.getEntityManager("mariadb")::persist);
        partTimeStudents.forEach(JPAUtil.getEntityManager("mariadb")::persist);
        enrollments.forEach(JPAUtil.getEntityManager("mariadb")::persist);
        JPAUtil.getEntityManager("mariadb").getTransaction().commit();
    }
}
