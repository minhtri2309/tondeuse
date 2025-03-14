package org.mower;

import java.util.List;

public class Mower {

    private final Position currentPosition;
    private final List<Instruction> instructionList;
    private final int[][] lawnGrid;

    public Mower(Position currentPosition, List<Instruction> instructionList, int[][] lawnGrid) {
        this.currentPosition = currentPosition;
        this.instructionList = instructionList;
        this.lawnGrid = lawnGrid;
    }

    /**
     * Start the mower, following its instructions
     */
    public void start() {
        for (Instruction currentInstruction : instructionList) {
            switch (currentInstruction){
                case Avance -> moveForward();
                case Droite, Gauche -> turn(currentInstruction);
                default -> throw new IllegalStateException("Unexpected value: " + currentInstruction);
            }
        }
        System.out.println(this);
    }

    /**
     * Move the mower by one unit in the current orientation.
     * If the moving is not possible because the mower will be out of bounds, the mower does not move.
     * Does not check if another mower is on new position.
     */
    private void moveForward() {
        int newX = this.currentPosition.getX();
        int newY = this.currentPosition.getY();

        switch (currentPosition.getOrientation()){
            case North -> newY++;
            case South -> newY--;
            case East -> newX++;
            case West -> newX--;
            default -> throw new IllegalStateException("Unexpected value: " + currentPosition.getOrientation());
        }

        if (isInBounds(newX, newY)){
            currentPosition.setX(newX);
            currentPosition.setY(newY);
        }
    }

    /**
     * Turn the orientation of mower
     *
     * @param instruction desired turn direction
     */
    private void turn(Instruction instruction) {
        Orientation newOrientation = instruction == Instruction.Droite
                ? currentPosition.getOrientation().getRightOrientation()
                : currentPosition.getOrientation().getLeftOrientation();

        currentPosition.setOrientation(newOrientation);
    }

    /**
     * @param x x coordinate on the lawn grid
     * @param y y coordinate on the lawn grid
     * @return true if x and y coordinates are within bounds
     */
    private boolean isInBounds(int x, int y) {
        return x >= 0 && x <= lawnGrid.length && y >= 0 && y <= lawnGrid[0].length;
    }

    @Override
    public String toString() {
        return "Mower is at (" + currentPosition.getX() + ", " + currentPosition.getY() + "), facing " + currentPosition.getOrientation();
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

}
