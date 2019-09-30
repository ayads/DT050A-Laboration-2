# DT050A-Distributed-Systems-laboration-2
In the second laboration you should use your basic implementation from Laboration 1, but now implement causal ordering (vector clocks) for your clients. Making so each client individually keeps track of the clock of each other client using a vector style clock.

## Goal
The main goal of this laboration is to implement causal ordring in the chat system.

## General tasks of laboration-2
- [ ] Implement causal ordering using vector clocks.
- [ ] Never display a message on the screen that is ahead of or out of order.

## Credit
- Ayad Shaif (aysh1500)
- Patrik Högblom(pahg1600)

## Definitions

### Vector Clock
Vector clocks are an improvement on Lamport clocks, in that it is possible to determine by examining their vector timestamps whether two events
are ordered by happened-before or are concurrent.