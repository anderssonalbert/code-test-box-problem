package se.callistaenterprise.boxproblem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.callistaenterprise.boxproblem.domain.Order;
import se.callistaenterprise.boxproblem.dto.BoxRequest;
import se.callistaenterprise.boxproblem.dto.BoxResponse;
import se.callistaenterprise.boxproblem.service.BoxService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/api/box",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BoxController {

    private final BoxService boxService;

    @PostMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoxResponse> selectBox(@Valid @RequestBody BoxRequest request) {

        var result = boxService.findSuitableBox(Order.of(request.articles()));
        return ResponseEntity.ok(new BoxResponse(result));
    }
}
