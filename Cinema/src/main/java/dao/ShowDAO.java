package dao;

import models.Show;

import java.util.List;

public class ShowDAO extends GenericDAO<Show, String> {
    public ShowDAO() {
        super(Show.class);
    }

//    - Chỉ cho phép cập nhật một buổi chiếu phim (show) nếu buổi chiếu phim đó chưa có khách hàng nào đặt
//    vé.
    @Override
    public boolean update(Show show) {
        String sql = "SELECT *\n" +
                "FROM shows s LEFT JOIN tickets t ON s.show_id = t.show_id\n" +
                "WHERE s.show_id = :id\n" +
                "GROUP BY s.show_id\n" +
                "HAVING count(t.ticket_number) = 0;";
        var result = em.createNativeQuery(sql, aClass)
                .setParameter("id", show.getId())
                .getResultList().isEmpty();

        if (result) return false;

        return super.update(show);
    }

//    Liệt kê các buổi chiếu phim (show) trong ngày hiện tại, chiếu những bộ phim (movie) do đạo diễn nào đó đạo diễn (tìm tương đối).
//            + listShowsByCurrentDateAndDirector(String director): List<Show>
    public List<Show> listShowsByCurrentDateAndDirector(String director) {
        String sql = "SELECT s FROM Show s JOIN s.movie m\n" +
                "WHERE m.director LIKE CONCAT('%', :director, '%') \n" +
                "AND FUNCTION('DATE', s.showDateTime) = CURRENT_DATE";
        return em.createQuery(sql, aClass)
                .setParameter("director", director)
                .getResultList();
    }
}
