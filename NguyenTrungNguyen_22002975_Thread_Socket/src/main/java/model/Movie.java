package model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    private int movieId;
    private String plot;
    private List<String> genres;
    private double runtime;
    private List<String> cast;
    private int numMflixComments;
    private String title;
    private List<String> languages;
    private List<String> directors;
    private String rated;
    private Award award;
    private int year;
}
