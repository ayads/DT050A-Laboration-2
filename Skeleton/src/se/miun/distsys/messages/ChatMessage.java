package se.miun.distsys.messages;

import se.miun.distsys.clients.Client;

public class ChatMessage extends Message {

	public String chat = "";
	
	public ChatMessage(Client client, String chat) {
		this.clientID = client.getID();
		this.chat = chat;
		if (ChatMessage.activeTimestampList.containsKey(this.clientID)){
            ChatMessage.activeTimestampList.put(this.clientID, ChatMessage.activeTimestampList.get(this.clientID) + 1);
        }else{
            this.timestamp = timestamp + 1;
            ChatMessage.activeTimestampList.put(this.clientID, this.timestamp);
        } 
	}
}
