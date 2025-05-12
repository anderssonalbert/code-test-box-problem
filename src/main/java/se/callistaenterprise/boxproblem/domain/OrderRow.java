package se.callistaenterprise.boxproblem.domain;

import static se.callistaenterprise.boxproblem.domain.Article.ARTICLES;

public record OrderRow(int quantity, Article article) {

    public static OrderRow of(int quantity, int articleId) {

        var article = ARTICLES.get(articleId);
        
        if (article == null) {
            throw new IllegalArgumentException("Article with ID=" + articleId + " does not exist");
        }

        return new OrderRow(quantity, article);
    }

    public int requiredArea() {
        return article.dimension().getArea() * quantity;
    }

}
