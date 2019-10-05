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

	public void print(HashMap<Integer, Integer> currentClientList) {
		currentClientList.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
    }


    public void handleCurrentClient(Message message, HashMap<Integer, Integer> currentClientList) {
    	HashMap<Integer, Integer> tempList = new HashMap<> ();
    	if(message instanceof ChatMessage) {
    		ChatMessage chatMessage = (ChatMessage) message;
    		if (currentClientList.containsKey(chatMessage.clientID)) {
    			int expectedTimestamp = currentClientList.get(chatMessage.clientID) + 1;
    			if (expectedTimestamp == chatMessage.timestamp || expectedTimestamp == tempList.get(chatMessage.clientID)) {
    				currentClientList.replace(chatMessage.clientID, chatMessage.timestamp);
    			} else {
    				tempList.put(chatMessage.clientID, chatMessage.timestamp);
    			}
    		} else {
    			currentClientList.put(chatMessage.clientID, chatMessage.timestamp);
    		}
    	} else if (message instanceof JoinMessage) {
    		JoinMessage joinMessage = (JoinMessage) message;
    		if (currentClientList.containsKey(joinMessage.clientID)) {
    			int expectedTimestamp = currentClientList.get(joinMessage.clientID) + 1;
    			if (expectedTimestamp == joinMessage.timestamp || expectedTimestamp == tempList.get(joinMessage.clientID)) {
    				currentClientList.replace(joinMessage.clientID, joinMessage.timestamp);
    			} else {
    				tempList.put(joinMessage.clientID, joinMessage.timestamp);
    			}
    		} else {
    			currentClientList.put(joinMessage.clientID, joinMessage.timestamp);
    		}
    	} else if (message instanceof JoinResponseMessage) {
    		JoinResponseMessage joinResponseMessage = (JoinResponseMessage) message;
    		if (currentClientList.containsKey(joinResponseMessage.clientID)) {
    			int expectedTimestamp = currentClientList.get(joinResponseMessage.clientID) + 1;
    			if (expectedTimestamp == joinResponseMessage.timestamp || expectedTimestamp == tempList.get(joinResponseMessage.clientID)) {
    				currentClientList.replace(joinResponseMessage.clientID, joinResponseMessage.timestamp);
    			} else {
    				tempList.put(joinResponseMessage.clientID, joinResponseMessage.timestamp);
    			}
    		} else {
    			currentClientList.put(joinResponseMessage.clientID, joinResponseMessage.timestamp);
    		}
    	} else if (message instanceof LeaveMessage) {
    		LeaveMessage leaveMessage = (LeaveMessage) message;
    		if (currentClientList.containsKey(leaveMessage.clientID)) {
    			int expectedTimestamp = currentClientList.get(leaveMessage.clientID) + 1;
    			if (expectedTimestamp == leaveMessage.timestamp || expectedTimestamp == tempList.get(leaveMessage.clientID)) {
    				currentClientList.replace(leaveMessage.clientID, leaveMessage.timestamp);
    			} else {
    				tempList.put(leaveMessage.clientID, leaveMessage.timestamp);
    			}
    		} else {
    			currentClientList.put(leaveMessage.clientID, leaveMessage.timestamp);
    		}
    	} else {
			System.out.println("Unknown message type");
		}
    }
}