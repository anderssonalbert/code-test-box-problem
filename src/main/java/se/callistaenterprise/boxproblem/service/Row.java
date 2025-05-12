package se.callistaenterprise.boxproblem.service;

import se.callistaenterprise.boxproblem.model.Article;

public record Row(int quantity, Article article) {

    public int requiredArea() {
        return article.dimension().getArea() * quantity;
    }
}
