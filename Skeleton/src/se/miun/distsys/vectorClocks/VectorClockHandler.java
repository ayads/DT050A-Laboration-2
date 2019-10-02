package se.miun.distsys.vectorClocks;

import se.miun.distsys.messages.Message;

public class VectorClockHandler {

    public void updateTimestamp(Message message){
        System.out.println(message.activeTimestamp.values());
    }

}