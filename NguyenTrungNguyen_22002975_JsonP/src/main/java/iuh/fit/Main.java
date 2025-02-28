package iuh.fit;

import iuh.fit.entities.Person;
import iuh.fit.util.PersonUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = PersonUtil.fromJson("json/people.json");
        if (people == null) {
            System.out.println("Failed to read people from file");
            return;
        }
        people.forEach(System.out::println);
    }
}
