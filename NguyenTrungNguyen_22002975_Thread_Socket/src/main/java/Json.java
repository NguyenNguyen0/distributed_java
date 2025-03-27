import model.Award;
import model.Movie;
import util.JsonUtil;

import java.io.FileNotFoundException;
import java.util.List;

public class Json {
    public static void main(String[] args) throws FileNotFoundException {
//        Movie movie = new Movie();
//        movie.setMovieId(11);
//        movie.setPlot("A thrilling adventure");
//        movie.setGenres(List.of("Action"));
//        movie.setRuntime(1200);
//        movie.setCast(List.of("John Doe", "Jane Smith"));
//        movie.setRuntime(120);
//        movie.setRated("PG-13");
//        movie.setNumMflixComments(2);
//        movie.setTitle("Adventure Time");
//        movie.setLanguages(List.of("English"));
//        movie.setDirectors(List.of("John Doe"));
//        movie.setRated("UnRated");
//        movie.setAward(new Award(1, 0, "1 win"));
//        movie.setYear(2025);
//
//        System.out.println("Json: " + JsonUtil.objectToJson(movie));

//        List<Movie> movies = JsonUtil.readJsonFile("json/data.json");
//        System.out.println("Json: " + movies);
        System.out.println("Cast: " + JsonUtil.listCasts(66L));
        JsonUtil.writeCastsToFile(66L, "json/output.txt");
    }
}
