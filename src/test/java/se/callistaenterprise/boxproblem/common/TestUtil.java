package se.callistaenterprise.boxproblem.common;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

public class TestUtil {

    // Example inputs and expected responses from specification
    static Stream<Arguments> sampleInput() {
        return Stream.of(
                Arguments.of(Map.of(7, 6, 4, 2, 1, 4), "2"),
                Arguments.of(Map.of(3, 3, 1, 1, 2, 1), "1"),
                Arguments.of(Map.of(5, 1, 4, 3), "2"),
                Arguments.of(Map.of(7, 12, 1, 100), "Upphämtning krävs"),
                Arguments.of(Map.of(8, 4), "1")
        );
    }
}
