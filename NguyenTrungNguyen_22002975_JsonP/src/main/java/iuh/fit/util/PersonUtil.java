package iuh.fit.util;

import iuh.fit.entities.Address;
import iuh.fit.entities.Person;
import iuh.fit.entities.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonUtil {
    public static List<Person> fromJson(String filename) {
        try (InputStream fis = new FileInputStream(filename);
            JsonReader reader = Json.createReader(fis)) {

            JsonArray jsonArray = reader.readArray();
            return jsonArray.stream()
                    .map(jsonObject -> jsonObjectToPerson((JsonObject) jsonObject))
                    .toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Person jsonObjectToPerson(JsonObject jsonObject) {
        if (jsonObject == null) return null;
        Person person = new Person();
        person.setFirstName(jsonObject.getString("firstName"));
        person.setLastName(jsonObject.getString("lastName"));
        person.setAge(jsonObject.getInt("age"));
        person.setAddress(jsonObjectToAddress(jsonObject.getJsonObject("address")));
        JsonArray phoneNumbersArray = jsonObject.getJsonArray("phoneNumbers");
        if (phoneNumbersArray == null) return person;
        List<PhoneNumber> phoneNumbers = phoneNumbersArray.stream()
                .map(jsonValue -> jsonObjectToPhoneNumber((JsonObject) jsonValue))
                .toList();

        person.setPhoneNumbers(phoneNumbers);
        return person;
    }

    public static Address jsonObjectToAddress(JsonObject jsonObject) {
        if (jsonObject == null) return null;
        Address address = new Address();
        address.setStreetAddress(jsonObject.getString("streetAddress"));
        address.setCity(jsonObject.getString("city"));
        address.setState(jsonObject.getString("state"));
        address.setPostalCode(jsonObject.getInt("postalCode"));
        return address;
    }

    public static PhoneNumber jsonObjectToPhoneNumber(JsonObject jsonObject) {
        if (jsonObject == null) return null;
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setType(jsonObject.getString("type"));
        phoneNumber.setNumber(jsonObject.getString("number"));
        return phoneNumber;
    }
}
