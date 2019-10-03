import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.EventQueue;
import java.awt.Color;

import se.miun.distsys.GroupCommuncation;
import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.listeners.JoinMessageListener;
import se.miun.distsys.listeners.LeaveMessageListener;
import se.miun.distsys.listeners.JoinResponseMessageListener;
import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;
import se.miun.distsys.messages.JoinResponseMessage;

public class WindowProgram implements ChatMessageListener, JoinMessageListener, LeaveMessageListener, JoinResponseMessageListener, ActionListener {
	JFrame frame;
	JTextPane txtpnChat = new JTextPane();
	JTextPane txtpnMessage = new JTextPane();
	JTextPane txtpnStatus = new JTextPane();

	GroupCommuncation gc = null;

	public WindowProgram() {
		initializeFrame();
		gc = new GroupCommuncation();
		gc.setChatMessageListener(this);
		gc.setJoinMessageListener(this);
		gc.setJoinResponseMessageListener(this);
		gc.setLeaveMessageListener(this);
		System.out.println("Group Communcation Started");
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(txtpnChat);
		txtpnChat.setEditable(false);	
		txtpnChat.setText("--== Group Chat ==--");
		
		txtpnMessage.setText(" ➔ ");
		frame.getContentPane().add(txtpnMessage);
		
		JButton btnSendChatMessage = new JButton("Send Chat Message");
		btnSendChatMessage.addActionListener(this);
		btnSendChatMessage.setActionCommand("send");
		frame.getContentPane().add(btnSendChatMessage);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
	            gc.shutdown();
	        }
		});
		JScrollPane scrollPaneStatus = new JScrollPane();
		frame.getContentPane().add(scrollPaneStatus);
		scrollPaneStatus.setViewportView(txtpnStatus);
		txtpnStatus.setEditable(false);
		Color backgroundColor = new Color(217, 236, 255);
		txtpnStatus.setBackground(backgroundColor);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equalsIgnoreCase("send")) {
			gc.sendChatMessage(gc.activeClient, txtpnMessage.getText());
		}		
	}

	@Override
	public void onIncomingChatMessage(ChatMessage chatMessage) {
		gc.vectorClockHandler.print();
		txtpnChat.setText(chatMessage.clientID + chatMessage.chat + "\n" + txtpnChat.getText());
	}

	@Override
	public void onIncomingJoinMessage(JoinMessage joinMessage) {
		try {
			gc.vectorClockHandler.print();
			gc.causallyOrderedClientList.put(joinMessage.clientID, 100);
			txtpnStatus.setText(joinMessage.clientID + " join." + "\n" + txtpnStatus.getText());
			if(joinMessage.clientID != gc.activeClient.getID()){				
				gc.sendJoinResponseMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onIncomingJoinResponseMessage(JoinResponseMessage joinResponseMessage) {
		try {
			if (!gc.causallyOrderedClientList.containsKey(joinResponseMessage.clientID)){
				gc.vectorClockHandler.print();
				gc.causallyOrderedClientList.put(joinResponseMessage.clientID, 100);
				txtpnStatus.setText(joinResponseMessage.clientID + " join response." + "\n" + txtpnStatus.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onIncomingLeaveMessage(LeaveMessage leaveMessage) {
		try {
			if (gc.causallyOrderedClientList.containsKey(leaveMessage.clientID)){
				gc.vectorClockHandler.print();
				txtpnStatus.setText(leaveMessage.clientID + " left." + "\n" + txtpnStatus.getText());
				gc.causallyOrderedClientList.remove(leaveMessage.clientID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowProgram window = new WindowProgram();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
