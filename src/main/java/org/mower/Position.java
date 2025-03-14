package org.mower;

public class Position {

    private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public Position(String[] position) {
        setPositionFromStrings(position);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setPositionFromStrings(String[] position) {
        this.x = Integer.valueOf(position[0]);
        this.y = Integer.valueOf(position[1]);
        this.orientation = Orientation.convertTo(position[2]);
    }

}
