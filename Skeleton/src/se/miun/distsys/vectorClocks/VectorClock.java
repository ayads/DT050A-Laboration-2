package se.miun.distsys.vectorClocks;

import java.util.Vector;

public class VectorClock {

    private int timestamp = 0;
    public Vector<Integer> clockEntry = new Vector();

    public VectorClock vectorClock() {
        return new VectorClock();
    }

    public VectorClock updateVectorClock() {
        return new VectorClock();
    }

    public VectorClock showVectorClock() {
        return new VectorClock();
    }
}