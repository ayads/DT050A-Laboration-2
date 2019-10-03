package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class JoinResponseMessage extends Message {

	public JoinResponseMessage(Client client) {
        this.clientID = client.getID();
	}
}