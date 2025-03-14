package org.mower;

import java.util.ArrayList;
import java.util.List;

public class MowerService {

    private final int[][] lawnGrid;
    private final List<Mower> mowerList;

    public MowerService(int xMax, int yMax) {
        this.lawnGrid = new int[xMax][yMax];
        this.mowerList = new ArrayList<>();
    }

    /**
     * Start all mowers
     */
    public void start() {
        this.mowerList.forEach(Mower::start);
    }

    public void addMower(Position initialPositionValue, List<Instruction> instructionList) {
        Mower mower = new Mower(initialPositionValue, instructionList, this.lawnGrid);
        this.mowerList.add(mower);
    }

    public Mower getMower(int index) {
        return this.mowerList.get(index);
    }
}
