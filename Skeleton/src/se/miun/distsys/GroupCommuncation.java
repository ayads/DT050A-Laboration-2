package se.miun.distsys;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.listeners.JoinMessageListener;
import se.miun.distsys.listeners.LeaveMessageListener;
import se.miun.distsys.listeners.JoinResponseMessageListener;
import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;
import se.miun.distsys.messages.Message;
import se.miun.distsys.messages.MessageSerializer;
import se.miun.distsys.vectorClocks.VectorClockHandler;
import se.miun.distsys.messages.JoinResponseMessage;
import se.miun.distsys.clients.Client;
import se.miun.distsys.clients.UniqueIdentifier;

public class GroupCommuncation {
	private int datagramSocketPort = 2019;	
	DatagramSocket datagramSocket = null;	
	boolean runGroupCommuncation = true;	
	MessageSerializer messageSerializer = new MessageSerializer();
	
	//Message Listeners
	ChatMessageListener chatMessageListener = null;
	JoinMessageListener joinMessageListener = null;
	LeaveMessageListener leaveMessageListener = null;
	JoinResponseMessageListener joinResponseMessageListener = null;

	//Create a new client.
	public Client activeClient = createClient();
	public HashMap<Integer, Integer> activeClientList = new HashMap();
	public VectorClockHandler vectorClockHandler = new VectorClockHandler();

	public GroupCommuncation() {
		try {
			runGroupCommuncation = true;
			datagramSocket = new MulticastSocket(datagramSocketPort);	
			ReceiveThread rt = new ReceiveThread();
			rt.start();
			sendJoinMessage(activeClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		sendLeaveMessage();
		runGroupCommuncation = false;
	}

	class ReceiveThread extends Thread{
		@Override
		public void run() {
			byte[] buffer = new byte[65536];		
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
			while(runGroupCommuncation) {
				try {
					datagramSocket.receive(datagramPacket);										
					byte[] packetData = datagramPacket.getData();					
					Message receivedMessage = messageSerializer.deserializeMessage(packetData);				
					handleMessage(receivedMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private void handleMessage (Message message) {
			if(message instanceof ChatMessage) {
				ChatMessage chatMessage = (ChatMessage) message;
				if(chatMessageListener != null){
					chatMessageListener.onIncomingChatMessage(chatMessage);
				}
			} else if (message instanceof JoinMessage) {
				JoinMessage joinMessage = (JoinMessage) message;
				if (joinMessageListener != null) {
					joinMessageListener.onIncomingJoinMessage(joinMessage);
				}
			} else if (message instanceof JoinResponseMessage) {
				JoinResponseMessage joinResponseMessage = (JoinResponseMessage) message;
				if (joinResponseMessageListener != null) {
					joinResponseMessageListener.onIncomingJoinResponseMessage(joinResponseMessage);
				}
			}  else if (message instanceof LeaveMessage) {
				LeaveMessage leaveMessage = (LeaveMessage) message;
				if (leaveMessageListener != null) {
					leaveMessageListener.onIncomingLeaveMessage(leaveMessage);
				}
			}
			else {
				System.out.println("Unknown message type");
			}
		}
	}
	
	public Client createClient() {
		try {
			Thread.sleep(250);
			Client activeClient = new Client(InetAddress.getByName("255.255.255.255"), datagramSocketPort, 
					UniqueIdentifier.getUniqueIdentifier());
			return activeClient;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void printActiveClientList(HashMap<Integer, Integer> activeClientList) { 
		for (Map.Entry activeClientEntry : activeClientList.entrySet()) {
			System.out.println("Client ID: " + activeClientEntry.getKey() + "\t" + "Timestamp: " + activeClientEntry.getValue());
		}
    } 
	
	public void sendChatMessage(Client client, String chat) {
		try {
			ChatMessage chatMessage = new ChatMessage(client, chat);
			vectorClockHandler.updateTimestamp(chatMessage);
			byte[] sendData = messageSerializer.serializeMessage(chatMessage);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
					InetAddress.getByName("255.255.255.255"), datagramSocketPort);
			datagramSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendJoinMessage(Client client) {
		try {
			JoinMessage joinMessage = new JoinMessage(client);
			vectorClockHandler.updateTimestamp(joinMessage);
			byte[] sendData = messageSerializer.serializeMessage(joinMessage);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
					InetAddress.getByName("255.255.255.255"), datagramSocketPort);
			datagramSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendJoinResponseMessage() {
		try {
			JoinResponseMessage joinResponseMessage = new JoinResponseMessage(activeClient);
			vectorClockHandler.updateTimestamp(joinResponseMessage);
			byte[] sendData = messageSerializer.serializeMessage(joinResponseMessage);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
					InetAddress.getByName("255.255.255.255"), datagramSocketPort);
			datagramSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendLeaveMessage() {
		try {
			LeaveMessage leaveMessage = new LeaveMessage(activeClient);
			byte[] sendData = messageSerializer.serializeMessage(leaveMessage);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
					InetAddress.getByName("255.255.255.255"), datagramSocketPort);
			datagramSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setChatMessageListener(ChatMessageListener listener) {
		this.chatMessageListener = listener;
	}

	public void setJoinMessageListener(JoinMessageListener listener) {
		this.joinMessageListener = listener;
	}

	public void setJoinResponseMessageListener(JoinResponseMessageListener listener) {
		this.joinResponseMessageListener = listener;
	}

	public void setLeaveMessageListener(LeaveMessageListener listener) {
		this.leaveMessageListener = listener;
	}
}
