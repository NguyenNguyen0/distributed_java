import dao.MovieDAO;
import dao.ShowDAO;
import models.Movie;
import utils.JPAUtil;

public class Main {
    public static void main(String[] args) {
//        ShowDAO showDAO = new ShowDAO();
//        System.out.println(showDAO.listShowsByCurrentDateAndDirector("Frank"));
        MovieDAO movieDAO = new MovieDAO();
//        System.out.println(movieDAO.listTicketSalesByMovieSortedByTitle());
        movieDAO.listTicketSalesByMovieSortedByTitle().forEach((m, n) -> {
            System.out.println(m.getTitle() + ": " + n);
        });
    }
}
