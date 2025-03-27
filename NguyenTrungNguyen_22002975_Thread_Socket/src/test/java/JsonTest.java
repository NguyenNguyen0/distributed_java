import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import util.JsonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    @TempDir
    Path tempDir;

    private File testJsonFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary JSON file with test data
        testJsonFile = tempDir.resolve("test_data.json").toFile();

        String testJson = """
        [{
          "movie_id": 11,
          "plot": "Charlie is an immigrant..",
          "genres": ["Comedy", "Short", "Drama"],
          "runtime": 30,
          "cast": ["Charles Chaplin", "EDNA PURVIANCE", "Eric Campbell", "Albert Austin"],
          "num_mflix_comments": 0,
          "title": "The Immigrant",
          "languages": ["English"],
          "directors": ["Charles Chaplin"],
          "rated": "UNRATED",
          "awards": {
            "wins": 1,
            "nominations": 0,
            "text": "1 win."
          },
          "year": 1917
        },
        {
          "movie_id": 66,
          "plot": "A criminal mastermind develops a plan to steal from a casino...",
          "genres": ["Crime", "Thriller", "Comedy"],
          "runtime": 116,
          "cast": ["George Clooney", "Brad Pitt", "Matt Damon", "Julia Roberts"],
          "num_mflix_comments": 134,
          "title": "Casino Heist",
          "languages": ["English", "Italian"],
          "directors": ["Steven Soderbergh"],
          "rated": "PG-13",
          "awards": {
            "wins": 1,
            "nominations": 2,
            "text": "1 win & 2 nominations."
          },
          "year": 2001
        }]
        """;

        try (FileWriter writer = new FileWriter(testJsonFile)) {
            writer.write(testJson);
        }
    }

    @Test
    void listCasts_shouldReturnCastForSpecifiedMovieId() throws FileNotFoundException {
        // Test finding cast for movie with ID 66
        List<String> cast = JsonUtil.listCasts(66L);

        // Verify result
        assertNotNull(cast);
        assertEquals(4, cast.size());
        assertEquals("George Clooney", cast.get(0));
        assertEquals("Brad Pitt", cast.get(1));
        assertEquals("Matt Damon", cast.get(2));
        assertEquals("Julia Roberts", cast.get(3));
    }

    @Test
    void listCasts_shouldReturnEmptyListForNonexistentMovieId() throws FileNotFoundException {
        // Test with a movie ID that doesn't exist in the test data
        List<String> cast = JsonUtil.listCasts(999L);

        // Should return an empty list or handle the case appropriately
        assertTrue(cast == null || cast.isEmpty());
    }
}