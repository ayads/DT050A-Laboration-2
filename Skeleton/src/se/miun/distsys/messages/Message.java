package se.miun.distsys.messages;

import java.io.Serializable;
import java.util.HashMap;

public class Message implements Serializable{
    public int clientID = 0;
    public int timestamp = 0;
    public static HashMap<Integer, Integer> activeTimestampList = new HashMap<Integer, Integer>();
}