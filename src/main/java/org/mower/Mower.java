package org.mower;

import java.util.List;

public class Mower {

    private Position currentPosition;
    private List<Instruction> instructionList;
    private int[][] lawnGrid;

    public Mower() {
    }

    /**
     * Start the mower, following its instructions
     */
    public void start() {
        for (Instruction currentInstruction : instructionList) {
            if (currentInstruction == Instruction.Avance) {
                moveForward();
            } else if (currentInstruction == Instruction.Droite || currentInstruction == Instruction.Gauche) {
                turn(currentInstruction);
            } else {
                throw new IllegalStateException("Unexpected value: " + currentInstruction);
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
        if (currentPosition.getOrientation() == Orientation.North) {
            int newY = currentPosition.getY() + 1;
            if (isYInBound(newY)) {
                currentPosition.setY(newY);
            }
        } else if (currentPosition.getOrientation() == Orientation.South) {
            int newY = currentPosition.getY() - 1;
            if (isYInBound(newY)) {
                currentPosition.setY(newY);
            }
        } else if (currentPosition.getOrientation() == Orientation.East) {
            int newX = currentPosition.getX() + 1;
            if (isXInBound(newX)) {
                currentPosition.setX(newX);
            }
        } else if (currentPosition.getOrientation() == Orientation.West) {
            int newX = currentPosition.getX() - 1;
            if (isXInBound(newX)) {
                currentPosition.setX(newX);
            }
        }
    }

    /**
     * Turn the orientation of mower
     *
     * @param instruction desired turn direction
     */
    private void turn(Instruction instruction) {
        if (instruction == Instruction.Droite) {
            Orientation newOrientation = Orientation.convertTo(currentPosition.getOrientation().rightOrientation);
            currentPosition.setOrientation(newOrientation);
        } else if (instruction == Instruction.Gauche) {
            Orientation newOrientation = Orientation.convertTo(currentPosition.getOrientation().leftOrientation);
            currentPosition.setOrientation(newOrientation);
        }
    }

    /**
     * @param x x coordinate on the lawn grid
     * @return true if x coordinate is not out of bounds
     */
    private boolean isXInBound(int x) {
        return x <= lawnGrid.length && x >= 0;
    }

    /**
     * @param y y coordinate on the lawn grid
     * @return true if y coordinate is not out of bounds
     */
    private boolean isYInBound(int y) {
        return y <= lawnGrid[0].length && y >= 0;
    }

    @Override
    public String toString() {
        return "Mower is at (" + currentPosition.getX() + ", " + currentPosition.getY() + "), facing " + currentPosition.getOrientation();
    }

    public void setLawnGrid(int[][] lawnGrid) {
        this.lawnGrid = lawnGrid;
    }

    public void setInstructionList(List<Instruction> instructionList) {
        this.instructionList = instructionList;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
}
