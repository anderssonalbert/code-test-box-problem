package se.callistaenterprise.boxproblem.domain;

import java.util.Objects;

import static se.callistaenterprise.boxproblem.domain.Article.ARTICLES;

public record OrderRow(int quantity, Article article) {

    public static OrderRow of(int quantity, int articleId) {
        var article = Objects.requireNonNull(ARTICLES.get(articleId), "Article with ID=" + articleId + " does not exist");
        return new OrderRow(quantity, article);
    }

    public int requiredArea() {
        return article.dimension().getArea() * quantity;
    }

}
