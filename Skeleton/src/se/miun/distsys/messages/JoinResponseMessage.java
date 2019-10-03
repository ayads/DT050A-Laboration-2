package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class JoinResponseMessage extends Message {

    public int clientID;
    public int timestamp;

	public JoinResponseMessage(Client client) {
		this.clientID = client.getID();
		if (JoinResponseMessage.activeTimestampList.containsKey(this.clientID)){
            this.timestamp = JoinMessage.activeTimestampList.get(this.clientID) + 1;
            JoinMessage.activeTimestampList.put(this.clientID, this.timestamp);
        }else{
            this.timestamp = timestamp + 1;
            JoinResponseMessage.activeTimestampList.put(this.clientID, this.timestamp);
        } 
	}
}