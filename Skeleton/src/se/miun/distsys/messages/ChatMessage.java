package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class ChatMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String chat = "";
	public int clientID;
	public int timestamp = 0;
	
	public ChatMessage(Client client, String chat) {
		this.clientID = client.getID();
		this.chat = chat;
	}
}
