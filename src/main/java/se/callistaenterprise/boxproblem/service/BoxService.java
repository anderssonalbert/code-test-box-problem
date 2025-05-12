package se.callistaenterprise.boxproblem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.callistaenterprise.boxproblem.domain.Order;

import java.util.Comparator;

import static se.callistaenterprise.boxproblem.domain.Box.BOXES;

@Service
@Slf4j
public class BoxService {
    public String findSuitableBox(Order order) {
        int totalArea = order.requiredArea();

        return BOXES.values().stream()
                .filter(box -> box.dimension().getArea() >= totalArea) // Filter boxes that can fit all items
                .min(Comparator.comparing(box -> box.dimension().getArea())) // Find the box smallest box
                .map(box -> String.valueOf(box.id())) // Convert the box ID to a String
                .orElse("Upphämtning krävs");
    }
}
