package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class LeaveMessage extends Message {

    public int clientID;
    public int timestamp;

	public LeaveMessage(Client client) {
        this.clientID = client.getID();
	}
}