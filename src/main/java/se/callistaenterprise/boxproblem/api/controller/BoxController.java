package se.callistaenterprise.boxproblem.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.callistaenterprise.boxproblem.api.dto.ResponseDto;
import se.callistaenterprise.boxproblem.api.dto.RowDto;
import se.callistaenterprise.boxproblem.service.BoxService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/api/boxes",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BoxController {
    private final BoxService boxService;

    @PostMapping(value = "/suitable", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findSuitableBox(@RequestBody List<RowDto> rows) {
        String result = boxService.findSmallestBox(rows);
        return ResponseEntity.ok(new ResponseDto(result));
    }
}