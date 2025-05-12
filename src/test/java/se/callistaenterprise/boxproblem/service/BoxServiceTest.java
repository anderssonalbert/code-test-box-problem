package se.callistaenterprise.boxproblem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.callistaenterprise.boxproblem.domain.Order;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoxServiceTest {

    BoxService boxService;

    @BeforeEach
    void setUp() {
        boxService = new BoxService();
    }

    @ParameterizedTest
    @MethodSource("sampleInput")
    void findValidBoxes(Map<Integer, Integer> articles, String expectedBoxId) {
        String actualId = boxService.findSuitableBox(Order.of(articles));
        assertEquals(expectedBoxId, actualId);
    }

    @Test
    void orderWithInvalidArticleIdThrowsException() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> Order.of(Map.of(99, 100)));
        assertEquals("Article with ID=99 does not exist", nullPointerException.getMessage());
    }

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