package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class LeaveMessage extends Message {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int clientID;
    public int timestamp;

	public LeaveMessage(Client client) {
		this.clientID = client.getID();
		if (LeaveMessage.currentClient.containsKey(this.clientID)){
            this.timestamp = JoinMessage.currentClient.get(this.clientID) + 1;
            JoinMessage.currentClient.put(this.clientID, this.timestamp);
        }else{
            this.timestamp = timestamp + 1;
            LeaveMessage.currentClient.put(this.clientID, this.timestamp);
        } 
	}
}