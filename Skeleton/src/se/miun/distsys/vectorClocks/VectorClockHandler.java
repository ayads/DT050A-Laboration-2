package se.miun.distsys.vectorClocks;

import java.util.HashMap;

import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.JoinResponseMessage;
import se.miun.distsys.messages.LeaveMessage;
import se.miun.distsys.messages.Message;

public class VectorClockHandler extends Message{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void print(HashMap<Integer, Integer> holdBackQueue) {
		System.out.println("-----=====|Hold-Back Queue Message List|=====-----");
		holdBackQueue.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
		System.out.println("\n");
	}
	
	public void vectorClockHandler(Message message, HashMap<Integer, Integer> holdBackQueue) {
		//Need to be able to classify which timestamp to accept, reject or buffer.
	}

    public void handleCurrentClient(Message message, HashMap<Integer, Integer> holdBackQueue) {
    	HashMap<Integer, Integer> bufferList = new HashMap<> ();
    	if(message instanceof ChatMessage) {
    		ChatMessage chatMessage = (ChatMessage) message;
    		if (holdBackQueue.containsKey(chatMessage.clientID)) {
    			int expectedTimestamp = holdBackQueue.get(chatMessage.clientID) + 1;
    			if (expectedTimestamp == chatMessage.timestamp || expectedTimestamp == bufferList.get(chatMessage.clientID)) {
    				holdBackQueue.replace(chatMessage.clientID, chatMessage.timestamp);
    			} else {
					System.out.println("Rejected: ---------->" + chatMessage.clientID + "\t" + chatMessage.timestamp);
    				bufferList.put(chatMessage.clientID, chatMessage.timestamp);
    			}
    		} else {
    			holdBackQueue.put(chatMessage.clientID, chatMessage.timestamp);
    		}
    	} else if (message instanceof JoinMessage) {
    		JoinMessage joinMessage = (JoinMessage) message;
    		if (holdBackQueue.containsKey(joinMessage.clientID)) {
    			int expectedTimestamp = holdBackQueue.get(joinMessage.clientID) + 1;
    			if (expectedTimestamp == joinMessage.timestamp || expectedTimestamp == bufferList.get(joinMessage.clientID)) {
    				holdBackQueue.replace(joinMessage.clientID, joinMessage.timestamp);
    			} else {
    				bufferList.put(joinMessage.clientID, joinMessage.timestamp);
    			}
    		} else {
    			holdBackQueue.put(joinMessage.clientID, joinMessage.timestamp);
    		}
    	} else if (message instanceof JoinResponseMessage) {
    		JoinResponseMessage joinResponseMessage = (JoinResponseMessage) message;
    		if (holdBackQueue.containsKey(joinResponseMessage.clientID)) {
    			int expectedTimestamp = holdBackQueue.get(joinResponseMessage.clientID) + 1;
    			if (expectedTimestamp == joinResponseMessage.timestamp || expectedTimestamp == bufferList.get(joinResponseMessage.clientID)) {
    				holdBackQueue.replace(joinResponseMessage.clientID, joinResponseMessage.timestamp);
    			} else {
    				bufferList.put(joinResponseMessage.clientID, joinResponseMessage.timestamp);
    			}
    		} else {
    			holdBackQueue.put(joinResponseMessage.clientID, joinResponseMessage.timestamp);
    		}
    	} else if (message instanceof LeaveMessage) {
    		LeaveMessage leaveMessage = (LeaveMessage) message;
    		if (holdBackQueue.containsKey(leaveMessage.clientID)) {
    			int expectedTimestamp = holdBackQueue.get(leaveMessage.clientID) + 1;
    			if (expectedTimestamp == leaveMessage.timestamp || expectedTimestamp == bufferList.get(leaveMessage.clientID)) {
    				holdBackQueue.replace(leaveMessage.clientID, leaveMessage.timestamp);
    			} else {
    				bufferList.put(leaveMessage.clientID, leaveMessage.timestamp);
    			}
    		} else {
    			holdBackQueue.put(leaveMessage.clientID, leaveMessage.timestamp);
    		}
    	} else {
			System.out.println("Unknown message type");
		}
	}
	
}