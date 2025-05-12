package se.callistaenterprise.boxproblem.repository;

import org.springframework.stereotype.Repository;
import se.callistaenterprise.boxproblem.model.Article;
import se.callistaenterprise.boxproblem.model.Dimension;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticlesRepository {

    private static final List<Article> AVAILABLE_ARTICLES = List.of(
            new Article(1, new Dimension(1, 1)),
            new Article(2, new Dimension(1, 2)),
            new Article(3, new Dimension(1, 4)),
            new Article(4, new Dimension(1, 6)),
            new Article(5, new Dimension(1, 8)),
            new Article(6, new Dimension(1, 9)),
            new Article(7, new Dimension(1, 12)),
            new Article(8, new Dimension(1, 5)),
            new Article(9, new Dimension(1, 9))
    );

    /**
     * Finds an article by its ID.
     *
     * @param id the article ID to search for
     * @return the article if found, otherwise empty
     */
    public Optional<Article> findById(Integer id) {
        return AVAILABLE_ARTICLES.stream()
                .filter(article -> article.id().equals(id))
                .findFirst();
    }
}