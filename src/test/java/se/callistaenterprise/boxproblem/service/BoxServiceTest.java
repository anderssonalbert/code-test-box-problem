package se.callistaenterprise.boxproblem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import se.callistaenterprise.boxproblem.api.dto.RowDto;
import se.callistaenterprise.boxproblem.repository.ArticlesRepository;
import se.callistaenterprise.boxproblem.repository.BoxRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoxServiceTest {

    private BoxService boxService;

    @BeforeEach
    void setUp() {
        ArticlesRepository articlesRepository = new ArticlesRepository();
        BoxRepository boxRepository = new BoxRepository();
        boxService = new BoxService(articlesRepository, boxRepository);
    }

    @ParameterizedTest
    @MethodSource("se.callistaenterprise.boxproblem.common.TestUtil#sampleInput")
    void findSmallestBox_validArticles_returnsExpectedBoxId(List<RowDto> rows, String expectedBoxId) {
        String actualId = boxService.findSmallestBox(rows);
        assertEquals(expectedBoxId, actualId);
    }


    @Test
    void findSmallestBox_nonExistentArticleId_throwsIllegalArgumentException() {
        var exception = assertThrows(IllegalArgumentException.class, () -> boxService.findSmallestBox(List.of(new RowDto(1, 99))));
        assertEquals("Article with ID=99 does not exist", exception.getMessage());
    }

}