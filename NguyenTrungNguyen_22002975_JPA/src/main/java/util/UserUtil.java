package util;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUtil {
    public JsonArray userToJson(List<User> users) {
        JsonArrayBuilder jsonArrayBuilder = jakarta.json.Json.createArrayBuilder();
        JsonObjectBuilder jsonObjectBuilder = jakarta.json.Json.createObjectBuilder();

        users.stream().map(user -> {
            jsonObjectBuilder.add("id", user.getId());
            jsonObjectBuilder.add("username", user.getUsername());
            jsonObjectBuilder.add("password", user.getPassword());
            jsonObjectBuilder.add("email", user.getEmail());

            JsonArrayBuilder groupsArrayBuilder = jakarta.json.Json.createArrayBuilder();
            user.getGroups().forEach(group -> {
                JsonObjectBuilder groupObjectBuilder = jakarta.json.Json.createObjectBuilder();
                groupObjectBuilder.add("id", group.getId());
                groupObjectBuilder.add("name", group.getName());
                groupsArrayBuilder.add(groupObjectBuilder);
            });

            jsonObjectBuilder.add("groups", groupsArrayBuilder);

            return jsonObjectBuilder.build();
        }).forEach(jsonArrayBuilder::add);

        return jsonArrayBuilder.build();
    }

    public void writeJsonArrayToFile(JsonArray usersJson, File file) throws IOException {
        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        try (FileWriter fileWriter = new FileWriter(file)) {
            JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(fileWriter);
            jsonWriter.writeArray(usersJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
