package se.callistaenterprise.boxproblem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.callistaenterprise.boxproblem.api.dto.RowDto;
import se.callistaenterprise.boxproblem.model.Box;
import se.callistaenterprise.boxproblem.repository.ArticlesRepository;
import se.callistaenterprise.boxproblem.repository.BoxRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoxService {

    private final ArticlesRepository articlesRepository;
    private final BoxRepository boxRepository;

    /**
     * Finds the smallest suitable box for a list of articles.
     *
     * @param dtoRows the list of items to be packed
     * @return box ID or pickup message if no suitable box exists
     * @throws IllegalArgumentException if any article ID is not found
     */
    public String findSmallestBox(List<RowDto> dtoRows) {
        List<Row> rows = dtoRows.stream().map(this::mapToRow).toList();

        int requiredArea = calculateTotalRequiredArea(rows);

        return findSmallestBoxForArea(requiredArea)
                .map(box -> box.id().toString())
                .orElse("Upphämtning krävs");
    }

    /**
     * Calculates the total required area for all rows.
     *
     * @param rows the list of rows to calculate area for
     * @return the total required area
     */
    private int calculateTotalRequiredArea(List<Row> rows) {
        return rows.stream().mapToInt(Row::requiredArea).sum();
    }

    /**
     * Finds the smallest box that can accommodate the required area.
     *
     * @param requiredArea the minimum area needed
     * @return the smallest suitable box or empty if none found
     */
    private Optional<Box> findSmallestBoxForArea(int requiredArea) {
        return boxRepository.findAll().stream()
                .filter(box -> box.dimension().getArea() >= requiredArea) // Filter for boxes that can fit all articles
                .min(Comparator.comparing(box -> box.dimension().getArea())); // Find the box smallest box
    }

    /**
     * Map RowDTO to Row, finding Article by id
     *
     * @param rowDto the row data transfer object
     * @return the mapped Row entity
     * @throws IllegalArgumentException if the article ID doesn't exist
     */
    private Row mapToRow(RowDto rowDto) {
        return articlesRepository.findById(rowDto.id())
                .map(article -> new Row(rowDto.quantity(), article))
                .orElseThrow(() -> new IllegalArgumentException("Article with ID=" + rowDto.id() + " does not exist"));
    }
}
