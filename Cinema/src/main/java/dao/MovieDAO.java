package dao;

import models.Movie;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieDAO extends GenericDAO<Movie, String> {
    public MovieDAO() {
        super(Movie.class);
    }

//    - Thêm bộ phim mới, mã số bộ phim (id) phải bắt đầu là ký tự “M” theo sau ít nhất 3 ký số và thời lượng (duration) phải lớn hơn 0.
    @Override
    public boolean add(Movie movie) {
        if (!movie.getId().matches("M[\\d]{3}")) return false;
        if (movie.getDuration() <= 0) return false;

        return super.add(movie);
    }

//    Thống kê doanh số bán vé theo từng bộ phim, kết quả sắp xếp theo tựa phim.
//            + listTicketSalesByMovieSortedByTitle(): Map<Movie, Double>
    public Map<Movie, Double> listTicketSalesByMovieSortedByTitle() {
        String jpql = "SELECT m, SUM(t.price)\n" +
                "FROM Movie m JOIN Show s ON s.movie.id = m.id\n" +
                "JOIN Ticket t ON t.show.id = s.id\n" +
                "GROUP BY m.id ORDER BY m.title";
        return em.createQuery(jpql, Object[].class).getResultList()
                .stream()
                .collect(Collectors.toMap(
                        result -> (Movie) result[0],
                        result -> (Double) result[1],
                        (a, b) -> a, LinkedHashMap::new
                ));
    }
}
