package se.callistaenterprise.boxproblem.common;

import org.junit.jupiter.params.provider.Arguments;
import se.callistaenterprise.boxproblem.api.dto.RowDto;

import java.util.List;
import java.util.stream.Stream;

public class TestUtil {
    static Stream<Arguments> sampleInput() {
        return Stream.of(
                // 6 st artikel 7, 2 st artikel 4, 4 st artikel 1 => kartong nr 2
                Arguments.of(List.of(
                                new RowDto(6, 7),
                                new RowDto(2, 4),
                                new RowDto(4, 1)),
                        "2"),

                // 3 st artikel 3, 1 st artikel 1, 1 st artikel 2 => kartong nr 1
                Arguments.of(List.of(
                                new RowDto(3, 3),
                                new RowDto(1, 1),
                                new RowDto(1, 2)),
                        "1"),

                // 1 st artikel 5, 3 st artikel 4 => kartong nr 2
                Arguments.of(List.of(
                                new RowDto(1, 5),
                                new RowDto(3, 4)),
                        "2"),

                // 12 st artikel 7, 100 st artikel 1 => "Upphämtning krävs"
                Arguments.of(List.of(
                                new RowDto(12, 7),
                                new RowDto(100, 1)),
                        "Upphämtning krävs"),

                // 4 st artikel 8 => kartong nr 1
                Arguments.of(List.of(
                                new RowDto(4, 8)),
                        "1")
        );
    }
}
