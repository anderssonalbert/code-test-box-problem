package se.callistaenterprise.boxproblem.domain;

public record Dimension(int width, int height) {

    public int getArea() {
        return width * height;
    }
}
