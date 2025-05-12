package se.callistaenterprise.boxproblem.model;

public record Dimension(int width, int height) {

    public int getArea() {
        return width * height;
    }
}
