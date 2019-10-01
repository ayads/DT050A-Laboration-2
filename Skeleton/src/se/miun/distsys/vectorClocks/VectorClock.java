package se.miun.distsys.vectorClocks;

import java.util.HashMap;

import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.JoinResponseMessage;
import se.miun.distsys.messages.LeaveMessage;

public class VectorClock {
    //A List of all active clients.
	public HashMap<Integer, Integer> activeClientList = new HashMap();

    public int updateTimestamp (ChatMessage chatMessage){
        return chatMessage.timestamp + 1;
    }

    public int updateTimestamp (JoinMessage joinMessage){
        return joinMessage.timestamp + 1;
    }

    public int updateTimestamp (JoinResponseMessage joinResponseMessage){
        return joinResponseMessage.timestamp + 1;
    }

    public int updateTimestamp (LeaveMessage leaveMessage){
        return leaveMessage.timestamp + 1;
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