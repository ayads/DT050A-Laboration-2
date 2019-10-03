package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class JoinMessage extends Message {
    public int clientID;
    public int timestamp;

	public JoinMessage(Client client) {
        this.clientID = client.getID();
        if (JoinMessage.activeTimestampList.containsKey(this.clientID)){
            this.timestamp = JoinMessage.activeTimestampList.get(this.clientID) + 1;
            JoinMessage.activeTimestampList.put(this.clientID, this.timestamp);
        }else{
            this.timestamp = timestamp + 1;
            JoinMessage.activeTimestampList.put(this.clientID, this.timestamp);
        } 
    }
}