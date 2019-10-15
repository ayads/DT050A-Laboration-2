# DT050A-Distributed-Systems-laboration-2
In the second laboration you should use your basic implementation from Laboration 1, but now implement causal ordering (vector clocks) for your clients. Making so each client individually keeps track of the clock of each other client using a vector style clock.

## Goal
The main goal of this laboration is to implement causal ordring in the chat system.

## General tasks of laboration-2
- [X] Implement causal ordering using vector clocks.
- [X] Never display a message on the screen that is ahead of or out of order.

## Definitions

### Causal Ordering
If multicast(g, m) ➔ multicast(g, m'), where ➔ is the
happened-before relation induced only by messages sent between the members of g, then any correct process that delivers m' will deliver m before m'.

### Vector Clock
Vector clocks are an improvement on Lamport clocks, in that it is possible to determine by examining their vector timestamps whether two events
are ordered by happened-before or are concurrent.

## Run
The following snapshot shows the result obtained when the program was run three time with BOT messages in the `Multicast network`.
![running program](/Images/run_test.PNG)

## File Structure
```
.
├── se
│   └── miun
│       ├── distsys
│       │   ├── clients
│       │   │   └── Client.java
│       │   ├── listeners
│       │   │   ├── ChatMessageListener.java
│       │   │   ├── JoinMessageListener.java
│       │   │   ├── JoinResponseMessageListener.java
│       │   │   └── LeaveMessageListener.java
│       │   ├── messages
│       │   │   ├── ChatMessage.java
│       │   │   ├── JoinMessage.java
│       │   │   ├── JoinResponseMessage.java
│       │   │   ├── LeaveMessage.java
│       │   │   ├── Message.java
│       │   │   └── MessageSerializer.java
│       │   └── vectorClocks
│       │       └── VectorClockHandler.java
│       └── GroupCommunication.java
├── Program.java
└── WindowProgram.java
```
## Multicast with Vector Clock
The following diagram shows what happens when the program runs three times. For demonstration purposes the three processes will be assigen the letters A, B and C instead of client-IDs.
![multicasted messages with vector clocks](/Images/Multicast_Diagram.png)


### Vector Clock Handler
This section discribes `VectorClockHandler` class and its methods.
VectorClockHandler constructs with the joined client and an initiated vector clock with zero.
```java
public VectorClockHandler(Client client, HashMap<Integer, Integer> myVectorClock){
	this.myClientID = client.getID();
	this.myVectorClock = myVectorClock;
    myVectorClock.put(myClientID, 0);
}
```
`printVectorClock` is mainly used for debugging purposes and message tracking.
```java
public void printVectorClock(HashMap<Integer, Integer> myVectorClock) {
	myVectorClock.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
	System.out.println("\n");
}
```
`incrementVectorClock` is responsible for incrementing the vector clock when a new message is created.
```java
public void incrementVectorClock(Message message){
	int timestamp = myVectorClock.get(myClientID) + 1;
	myVectorClock.put(myClientID, timestamp);
	message.messageVectorClock = (HashMap<Integer, Integer>) myVectorClock.clone();
}
```
`isCausalOrder` compares the current vector clock with the received messages vector clock.
```java
public Boolean isCausalOrder (Message receivedMessage, HashMap<Integer, Integer> myVectorClock){
	if (receivedMessage.messageVectorClock.containsKey(myClientID) && receivedMessage.messageVectorClock.get(myClientID) > myVectorClock.ge(myClientID) ) {
		return false;
	}
	return true;
}
```

### Message
Here we took advantage of the fack that `HashMap`s are serializable by including it with created messages. For example whem a `JoinMessage` is initiated, it will contain its clientID and a `HashMap` for handeling its vecktor clock.
```java
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	public HashMap<Integer, Integer> messageVectorClock = null;
}
```
### Fuction Call
The following method shows how in comming messages are been handeled. We need first to check the causality of the messages before we can show them. Thats why we have an `if` statement for handeling it.
```java
@Override
public void onIncomingJoinMessage(JoinMessage joinMessage) {
	try {
		if(gc.vectorClockHandler.isCausalOrder(joinMessage, gc.vectorClock)){
			gc.activeClientList.add(joinMessage.clientID);
			txtpnStatus.setText(joinMessage.clientID + " join." + "\n" + txtpnStatus.getText());
		} else {
			txtpnCausalOrder.setText(joinMessage.clientID + " is out of order!" + "\n" + txtpnCausalOrder.getText());
		}
		if(joinMessage.clientID != gc.activeClient.getID()){				
			gc.sendJoinResponseMessage();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
```
