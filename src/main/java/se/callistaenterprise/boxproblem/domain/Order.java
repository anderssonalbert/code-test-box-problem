package se.callistaenterprise.boxproblem.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Order with multiple orderRows (quantity, article)
 */
public record Order(Map<Integer, OrderRow> orderRows) {

    /**
     * Create an Order from a map of article id / quantity
     */
    public static Order of(Map<Integer, Integer> itemQuantities) {
        Map<Integer, OrderRow> articles = new HashMap<>();

        for (Integer articleId : itemQuantities.keySet()) {
            articles.put(articleId, OrderRow.of(itemQuantities.get(articleId), articleId));
        }

        return new Order(Collections.unmodifiableMap(articles));
    }

    /**
     * Calculates the total area occupied by all orderRows in the order
     */
    public int requiredArea() {
        return orderRows.values().stream()
                .mapToInt(OrderRow::requiredArea)
                .sum();
    }
}
