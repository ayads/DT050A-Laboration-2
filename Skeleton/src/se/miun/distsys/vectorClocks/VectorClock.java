package se.miun.distsys.vectorClocks;

import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.JoinResponseMessage;
import se.miun.distsys.messages.LeaveMessage;

public class VectorClock {

    ChatMessage chatMessage;
    JoinMessage joinMessage;
    JoinResponseMessage joinResponseMessage;
    LeaveMessage leaveMessage;

    public ChatMessage updateTimestamp (ChatMessage chatMessage){
        this.chatMessage = chatMessage;
        chatMessage.timestamp = chatMessage.timestamp + 1;
        return chatMessage;
    }

    public JoinMessage updateTimestamp (JoinMessage joinMessage){
        this.joinMessage = joinMessage;
        joinMessage.timestamp = joinMessage.timestamp + 1;
        return joinMessage;
    }

    public JoinResponseMessage updateTimestamp (JoinResponseMessage joinResponseMessage){
        this.joinResponseMessage = joinResponseMessage;
        joinResponseMessage.timestamp = joinResponseMessage.timestamp + 1;
        return joinResponseMessage;
    }

    public LeaveMessage updateTimestamp (LeaveMessage leaveMessage){
        this.leaveMessage = leaveMessage;
        leaveMessage.timestamp = leaveMessage.timestamp + 1;
        return leaveMessage;
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