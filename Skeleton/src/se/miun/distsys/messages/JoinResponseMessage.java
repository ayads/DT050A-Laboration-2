package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class JoinResponseMessage extends Message {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int clientID;
    public int timestamp;

	public JoinResponseMessage(Client client) {
		this.clientID = client.getID();
		if (JoinResponseMessage.currentClient.containsKey(this.clientID)){
            this.timestamp = JoinMessage.currentClient.get(this.clientID) + 1;
            JoinMessage.currentClient.put(this.clientID, this.timestamp);
        }else{
            this.timestamp = timestamp + 1;
            JoinResponseMessage.currentClient.put(this.clientID, this.timestamp);
        } 
	}
}