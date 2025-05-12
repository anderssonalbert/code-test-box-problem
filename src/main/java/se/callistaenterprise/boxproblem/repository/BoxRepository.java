package se.callistaenterprise.boxproblem.repository;

import org.springframework.stereotype.Repository;
import se.callistaenterprise.boxproblem.model.Box;
import se.callistaenterprise.boxproblem.model.Dimension;

import java.util.List;

@Repository
public class BoxRepository {

    private static final List<Box> AVAILABLE_BOXES = List.of(
            new Box(1, new Dimension(4, 5)),
            new Box(2, new Dimension(8, 12)),
            new Box(3, new Dimension(12, 20))
    );

    /**
     * Retrieves all available boxes.
     *
     * @return an immutable list of all box options
     */
    public List<Box> findAll() {
        return AVAILABLE_BOXES;
    }
}
