package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class JoinResponseMessage extends Message {

	public JoinResponseMessage(Client client) {
		this.clientID = client.getID();
		if (JoinResponseMessage.activeTimestampList.containsKey(this.clientID)){
            JoinResponseMessage.activeTimestampList.put(this.clientID, JoinResponseMessage.activeTimestampList.get(this.clientID) + 1);
        }else{
            this.timestamp = timestamp + 1;
            JoinResponseMessage.activeTimestampList.put(this.clientID, this.timestamp);
        } 
	}
}