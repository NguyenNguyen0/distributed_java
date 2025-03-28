package util;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import model.Award;
import model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {
    public static String objectToJson(Movie movie) {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {
            jsonGenerator
                    .writeStartObject()
                    .write("movieId", movie.getMovieId())
                    .write("plot", movie.getPlot())
                    .writeStartArray("genres");



            jsonGenerator
                    .writeEnd()
                    .write("runtime", movie.getPlot())
                    .write("title", movie.getTitle())
                    .write("rated", movie.getRated())
                    .write("year", movie.getYear())
                    .writeEnd();
        }
        return stringWriter.toString();
    }

    public static void writeCastsToFile(Long movieId, String outputFileName) {
        List<String> casts = listCasts(movieId);
        if (casts == null || casts.isEmpty()) {
            System.out.println("No cast found for movie ID: " + movieId);
            return;
        }

        File outputFile = new File(outputFileName);
        try (java.io.FileWriter writer = new java.io.FileWriter(outputFile)) {
            writer.write("Cast for movie ID " + movieId + ":\n");
            for (String actor : casts) {
                writer.write(actor + "\n");
            }
            System.out.println("Cast list successfully written to " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error writing cast to file: " + e.getMessage());
            throw new RuntimeException("Failed to write cast to file", e);
        }
    }

    public static List<String> listCasts(Long movieId) {
        try {
            List<String> casts = readJsonFile("json/data.json").stream()
                    .filter((movie -> movie.getMovieId() == movieId))
                    .map(Movie::getCast).toList().getLast();
            return casts;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> listCasts(Long movieId, String filepath) {
        List<Movie> movies = readJsonFile(filepath);
        return movies.stream()
                .filter(movie -> movie.getMovieId() == movieId)
                .findFirst()
                .map(Movie::getCast)
                .orElse(List.of());
    }

    public static List<String> listCastsStream(String filepath, Long movieId) {
        File file = new File(filepath);
        List<String> casts = new ArrayList<>();
        String currentKey = "";
        Movie currentMovie = null;
        boolean isStringArray = false;
        boolean inAwardsObject = false;
        try (JsonParser parser = Json.createParser(new FileReader(file))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                System.out.println(currentMovie);
                switch (event) {
                    case START_ARRAY -> {
                        if (currentKey.isEmpty()) {
                            // This is the main array of movies
                            continue;
                        }
                        isStringArray = true;
                    }
                    case START_OBJECT -> {
                        if (currentKey.equals("awards")) {
                            inAwardsObject = true;
                            continue;
                        }
                        if (currentKey.isEmpty() && currentMovie == null) {
                            currentMovie = new Movie();
                        }
                    }

                    case KEY_NAME -> {
                        currentKey = parser.getString();
                    }

                    case VALUE_STRING -> {
                        System.out.println("currentKey: " + currentKey + " movieId: " + currentMovie.getMovieId());
                        System.out.println(isStringArray);
                        if (isStringArray && currentKey.equals("cast") && currentMovie.getMovieId() == movieId) {
                            casts.add(parser.getString());
                        }
                    }

                    case VALUE_NUMBER -> {
                        if (currentKey.equals("movie_id")) {
                            currentMovie.setMovieId(parser.getInt());
                        }
                    }

                    case END_OBJECT -> {
                        if (currentKey.equals("awards") || inAwardsObject) {
                            inAwardsObject = false;
                            continue;
                        }
                        if (currentMovie != null) {
                            currentMovie = null;
                            currentKey = "";
                        }
                    }
                    case END_ARRAY -> {
                        if (isStringArray) {
                            if (currentKey.equals("cast") && currentMovie.getMovieId() == movieId) {
                                return casts;
                            } else {
                                casts = new ArrayList<>();
                            }
                        }
                        isStringArray = false;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return casts;
    }

    public static List<Movie> readJsonFile(String filename) {
        File file = new File(filename);
        List<Movie> movies = new ArrayList<>();
        Movie currentMovie = null;
        String currentKey = "";
        List<String> currentStringArray = null;
        boolean inAwardsObject = false;
        int wins = 0;
        int nominations = 0;
        String awardText = "";

        try (JsonParser parser = Json.createParser(new FileReader(file))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY -> {
                        if (currentKey.isEmpty()) {
                            // This is the main array of movies
                            continue;
                        }

                        // Handle arrays of strings like genres, cast, languages, directors
                        currentStringArray = new ArrayList<>();
                    }

                    case START_OBJECT -> {
                        if (currentKey.equals("awards")) {
                            inAwardsObject = true;
                        } else if (currentKey.isEmpty()) {
                            // New movie object
                            currentMovie = new Movie();
                        }
                    }

                    case KEY_NAME -> {
                        if (currentMovie != null || inAwardsObject || currentKey.isEmpty()) {
                            currentKey = parser.getString();
                            // Convert snake_case to camelCase for some fields
                            if (currentKey.equals("movie_id")) {
                                currentKey = "movieId";
                            } else if (currentKey.equals("num_mflix_comments")) {
                                currentKey = "numMflixComments";
                            }
                        } else {
                            // We received a key but currentMovie is null and we're not in a valid context
                            throw new IllegalStateException("Received key '" + parser.getString() +
                                    "' but no movie object is being processed");
                        }
                    }

                    case VALUE_STRING -> {
                        if (inAwardsObject) {
                            if (currentKey.equals("text")) {
                                awardText = parser.getString();
                            }
                        } else if (currentStringArray != null) {
                            currentStringArray.add(parser.getString());
                        } else {
                            switch (currentKey) {
                                case "plot" -> currentMovie.setPlot(parser.getString());
                                case "title" -> currentMovie.setTitle(parser.getString());
                                case "rated" -> currentMovie.setRated(parser.getString());
                            }
                        }
                    }

                    case VALUE_NUMBER -> {
                        if (inAwardsObject) {
                            switch (currentKey) {
                                case "wins" -> wins = parser.getInt();
                                case "nominations" -> nominations = parser.getInt();
                            }
                        } else {
                            switch (currentKey) {
                                case "movieId" -> currentMovie.setMovieId(parser.getInt());
                                case "runtime" -> currentMovie.setRuntime(parser.getInt());
                                case "year" -> currentMovie.setYear(parser.getInt());
                                case "numMflixComments" -> currentMovie.setNumMflixComments(parser.getInt());
                            }
                        }
                    }

                    case END_ARRAY -> {
                        if (currentStringArray != null) {
                            switch (currentKey) {
                                case "genres" -> currentMovie.setGenres(currentStringArray);
                                case "cast" -> currentMovie.setCast(currentStringArray);
                                case "languages" -> currentMovie.setLanguages(currentStringArray);
                                case "directors" -> currentMovie.setDirectors(currentStringArray);
                            }
                            currentStringArray = null;
                            currentKey = "";
                        }
                    }

                    case END_OBJECT -> {
                        if (inAwardsObject) {
                            currentMovie.setAward(new Award(wins, nominations, awardText));
                            inAwardsObject = false;
                            currentKey = "";
                        } else {
                            movies.add(currentMovie);
                            currentMovie = null;
                            currentKey = "";
                        }
                    }

                    default -> throw new AssertionError("Unexpected event: " + event);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }
}
