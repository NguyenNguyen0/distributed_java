import dao.MovieDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMovieDAO {
    @Test
    void testListTicketSalesByMovieSortedByTitle() {
        MovieDAO movieDAO = new MovieDAO();
        var result = movieDAO.listTicketSalesByMovieSortedByTitle();
        assertTrue(result.size() > 0);
    }
}
