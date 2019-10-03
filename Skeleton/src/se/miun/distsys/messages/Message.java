package se.miun.distsys.messages;

import java.io.Serializable;
import java.util.HashMap;

public class Message implements Serializable{
    public static HashMap<Integer, Integer> activeTimestampList = new HashMap<>();
}