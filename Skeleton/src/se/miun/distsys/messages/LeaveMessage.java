package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class LeaveMessage extends Message {

	public LeaveMessage(Client client) {
		this.clientID = client.getID();
		if (LeaveMessage.activeTimestampList.containsKey(this.clientID)){
            LeaveMessage.activeTimestampList.put(this.clientID, LeaveMessage.activeTimestampList.get(this.clientID) + 1);
        }else{
            this.timestamp = timestamp + 1;
            LeaveMessage.activeTimestampList.put(this.clientID, this.timestamp);
        } 
	}
}