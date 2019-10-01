package se.miun.distsys.vectorClocks;

import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.JoinResponseMessage;
import se.miun.distsys.messages.LeaveMessage;

public class VectorClock {

    int timestamp = 0;

    public int updateTimestamp (JoinMessage joinMessageTimestamp){
        this.timestamp = joinMessageTimestamp.timestamp;
        return this.timestamp + 1;
    }

    public int updateTimestamp (JoinResponseMessage joinResponseMessageTimestamp){
        this.timestamp = joinResponseMessageTimestamp.timestamp;
        return this.timestamp + 1;
    }

    public int updateTimestamp (ChatMessage chatMessageTimestamp){
        this.timestamp = chatMessageTimestamp.timestamp;
        return this.timestamp + 1;
    }

    public int updateTimestamp (LeaveMessage leaveMessageTimestamp){
        this.timestamp = leaveMessageTimestamp.timestamp;
        return this.timestamp + 1;
    }

    /**
     * 
     * @param time
     * @return
     * public int updateTimestamp (int time){
     *  return Math.max(time, timestamp);
     * }
     */
}