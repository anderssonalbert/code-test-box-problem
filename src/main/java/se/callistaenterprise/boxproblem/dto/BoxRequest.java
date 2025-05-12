package se.callistaenterprise.boxproblem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record BoxRequest(
        @NotNull(message = "Articles must not be null")
        @NotEmpty(message = "Articles must not be empty")
        Map<Integer, Integer> articles) {
}
