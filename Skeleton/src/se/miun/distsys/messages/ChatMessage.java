package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class ChatMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String chat = "";
	public int clientID;
    public int timestamp;
	
	public ChatMessage(Client client, String chat) {
		this.clientID = client.getID();
		this.chat = chat;
		if (ChatMessage.currentClient.containsKey(this.clientID)){
			this.timestamp = ChatMessage.currentClient.get(this.clientID) + 1;
			ChatMessage.currentClient.put(this.clientID, this.timestamp);
        }else{
            this.timestamp = timestamp + 1;
            ChatMessage.currentClient.put(this.clientID, this.timestamp);
        } 
	}
}
