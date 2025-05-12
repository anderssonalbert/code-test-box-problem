package se.callistaenterprise.boxproblem.domain;

import java.util.Map;

public record Article(int id, Dimension dimension) {

    public static final Map<Integer, Article> ARTICLES = Map.of(
            1, new Article(1, new Dimension(1, 1)),
            2, new Article(2, new Dimension(1, 2)),
            3, new Article(3, new Dimension(1, 4)),
            4, new Article(4, new Dimension(1, 6)),
            5, new Article(5, new Dimension(1, 8)),
            6, new Article(6, new Dimension(1, 9)),
            7, new Article(7, new Dimension(1, 12)),
            8, new Article(8, new Dimension(1, 5)),
            9, new Article(9, new Dimension(1, 9))
    );
}
