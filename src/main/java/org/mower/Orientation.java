package org.mower;

public enum Orientation {

    North("N", "E", "W"),
    East("E", "S", "N"),
    West("W", "N", "S"),
    South("S", "W", "E");

    public final String orientation;
    public final String rightOrientation;
    public final String leftOrientation;

    Orientation(String orientation, String rightOrientation, String leftOrientation) {
        this.orientation = orientation;
        this.rightOrientation = rightOrientation;
        this.leftOrientation = leftOrientation;
    }

    public static Orientation convertTo(String value){
        switch (value){
            case "N" -> {
                return North;
            }
            case "E" -> {
                return East;
            }
            case "W" -> {
                return West;
            }
            case "S" -> {
                return South;
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }
}
