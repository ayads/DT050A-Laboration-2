package se.miun.distsys.messages;

import java.io.Serializable;
import java.util.HashMap;

public class Message implements Serializable{
    public HashMap<Integer, Integer> activeTimestamp = new HashMap<Integer, Integer>();
}