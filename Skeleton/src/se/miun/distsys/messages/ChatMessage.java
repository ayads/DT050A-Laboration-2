package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class ChatMessage extends Message {

	public String chat = "";
	public int clientID;
    public int timestamp;
	
	public ChatMessage(Client client, String chat) {
		this.clientID = client.getID();
		this.chat = chat;
		if (ChatMessage.activeTimestampList.containsKey(this.clientID)){
			this.timestamp = JoinMessage.activeTimestampList.get(this.clientID) + 1;
            JoinMessage.activeTimestampList.put(this.clientID, this.timestamp);
        }else{
            this.timestamp = timestamp + 1;
            ChatMessage.activeTimestampList.put(this.clientID, this.timestamp);
        } 
	}
}
