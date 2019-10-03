package se.miun.distsys.vectorClocks;

import se.miun.distsys.messages.Message;

public class VectorClock extends Message{

    public void print() {
        VectorClock.activeTimestampList.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
    }

    public int getSize() {
        System.out.println(VectorClock.activeTimestampList.size());
        return VectorClock.activeTimestampList.size();
    }
}