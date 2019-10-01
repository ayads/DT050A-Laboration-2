package se.miun.distsys.vectorClocks;


public class VectorClock {

    int timestamp;
    public int updateTimestamp (){
        return timestamp + 1;
    }

    public int updateTimestamp (int time){
        return Math.max(time, timestamp);
    }
}