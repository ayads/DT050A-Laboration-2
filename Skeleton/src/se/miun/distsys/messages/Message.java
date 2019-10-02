package se.miun.distsys.messages;

import java.io.Serializable;
import java.util.HashMap;

public class Message implements Serializable{
    public HashMap<Integer, Integer> activeClientList = new HashMap<Integer, Integer>();
}