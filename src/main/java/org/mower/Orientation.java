package org.mower;

/**
 * Orientation enum for mowers orientation
 */
public enum Orientation {

    North('N', 'E', 'W'),
    East('E', 'S', 'N'),
    West('W', 'N', 'S'),
    South('S', 'W', 'E');

    public final char orientation;
    public final char rightOrientation;
    public final char leftOrientation;

    Orientation(char orientation, char rightOrientation, char leftOrientation) {
        this.orientation = orientation;
        this.rightOrientation = rightOrientation;
        this.leftOrientation = leftOrientation;
    }

    public static Orientation convertTo(char value) {
        switch (value) {
            case 'N' -> {
                return North;
            }
            case 'E' -> {
                return East;
            }
            case 'W' -> {
                return West;
            }
            case 'S' -> {
                return South;
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    public Orientation getRightOrientation() {
        return convertTo(rightOrientation);
    }

    public Orientation getLeftOrientation() {
        return convertTo(leftOrientation);
    }
}
