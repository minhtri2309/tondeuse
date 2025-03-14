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

    public void addMower(Mower mower) {
        mower.setLawnGrid(lawnGrid);
        mowerList.add(mower);
    }

    public Mower getMower(int index) {
        return mowerList.get(index);
    }
}
