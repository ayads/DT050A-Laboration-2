package se.miun.distsys.vectorClocks;

import java.util.HashMap;

import se.miun.distsys.messages.Message;

public class VectorClockHandler extends Message{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void printSet(HashMap<Integer, Integer> clientList) {
		clientList.forEach((key, value) -> System.out.println("clientID: " + key + "\t" + "Timestamp: " + value));
		System.out.println("\n");
	}

    public void CausallyOrderControl(HashMap<Integer, Integer> holdBackQueue, HashMap<Integer, Integer> messageDeliveryList) {
		// If V[messageDeliveryList] < V[holdBack] then messageDelivery happen-before holdBack. //Causally ordered.
	}
}