package se.miun.distsys.vectorClocks;

import se.miun.distsys.messages.Message;

public class VectorClock {

    public void print(Message message) {
        message.activeTimestamp.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
    }

    public int size(Message message) {
        System.out.println(message.activeTimestamp.size());
        return message.activeTimestamp.size();
    }

    public void updateTimestamp(Message message) {
        message.activeTimestamp.put(message.clientID, message.activeTimestamp.get(message.clientID) + 1);
        //message.activeTimestamp.replace(message.clientID, message.activeTimestamp.get(message.clientID), message.activeTimestamp.get(message.clientID) + 1);
    }
}