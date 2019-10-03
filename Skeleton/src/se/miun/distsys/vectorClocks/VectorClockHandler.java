package se.miun.distsys.vectorClocks;

import se.miun.distsys.messages.Message;

public class VectorClockHandler extends Message{

    public void print() {
        VectorClockHandler.activeTimestampList.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
    }

    public int getSize() {
        System.out.println(VectorClockHandler.activeTimestampList.size());
        return VectorClockHandler.activeTimestampList.size();
    }

    public void compareTimestamp(Message message) {
        //Check if message timestamp in order!
        //Consider using replace if required!
    }
}