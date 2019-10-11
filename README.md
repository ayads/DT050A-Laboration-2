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
![multicasted messages with vector clocks](/Images/Multicast_Diagram.PNG)

## Credit
- Ayad Shaif (aysh1500)
- Patrik Högblom(pahg1600)