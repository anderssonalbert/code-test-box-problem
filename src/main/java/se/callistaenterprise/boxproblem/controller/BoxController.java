package se.callistaenterprise.boxproblem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.callistaenterprise.boxproblem.domain.Order;
import se.callistaenterprise.boxproblem.service.BoxService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/box")
public class BoxController {

    private final BoxService boxService;

    @PostMapping("/find")
    public ResponseEntity<BoxResponse> selectBox(@RequestBody BoxRequest request) {

        try {
            var result = boxService.findSuitableBox(Order.of(request.articles()));
            return ResponseEntity.ok(new BoxResponse(result));
        } catch (Exception e) {
            log.error("Error processing request", e);
            return ResponseEntity.badRequest().body(new BoxResponse("Error: " + e.getMessage()));
        }
    }

    public record BoxRequest(Map<Integer, Integer> articles) {
    }

    public record BoxResponse(String message) {
    }
}
