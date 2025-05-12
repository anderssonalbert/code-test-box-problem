package se.callistaenterprise.boxproblem.domain;

import java.util.Map;

public record Box(int id, Dimension dimension) {

    public static final Map<Integer, Box> BOXES = Map.of(
            1, new Box(1, new Dimension(4, 5)),
            2, new Box(2, new Dimension(8, 12)),
            3, new Box(3, new Dimension(12, 20))
    );
}
