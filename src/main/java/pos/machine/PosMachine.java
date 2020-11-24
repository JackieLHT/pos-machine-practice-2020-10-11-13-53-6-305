package pos.machine;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class PosMachine {
// P: 2 hours
// D: 1 hour
// C: Used helpful data structures like HashMap and List reduced a lot of development time, but as I am not familiar with Java data structure, googling takes a while
// A: Keep trying to use these data structures in future development work. Study more on java data structures
    public String printReceipt(List<String> barcodes) {
    	List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();
    	HashMap<String,Integer> itemCountMap = getItemCountMap(barcodes);
    	
    	List<ItemDetail> allItemDetails = getItemDetails(itemInfos,itemCountMap);
    	
    	int total = calculateTotal(allItemDetails);
    	
    	
        return renderReceipt(allItemDetails,total);
    }
    
 // P: 45 mins
 // D: 15 mins
 // C: At first it seemed complicated as I could only think of using loop to doing counting, but I searched for better methods online and have learnt to use HashMap to do it
 // A: Keep this in mind and try to adopt HashMap in similar tasks in the future. Study more on HashMap
    private HashMap<String,Integer> getItemCountMap(List<String> barcodes) {
    	HashMap<String, Integer> itemCountMap = new HashMap<String, Integer>();
    	for(String item:barcodes) {
    		if(!itemCountMap.containsKey(item))
    			itemCountMap.put(item, 1);
    		else itemCountMap.put(item, itemCountMap.get(item) + 1);
    	}
    	return itemCountMap;
    }
    
 // P: 45 mins
 // D: 25 mins
 // C: At first I planned to generate ItemDetail one by one, but it turns out I could iterate through the list and get the ItemDetail of all the items all at once, so it saved some time
 // A: Can use this experience to do better planning during context map stage
    
    private List<ItemDetail> getItemDetails(List<ItemInfo> itemInfos,HashMap<String,Integer> itemCountMap) {
    	List<ItemDetail> allItemDetails = new ArrayList<>();
    	for(ItemInfo item:itemInfos) {
    		if(itemCountMap.get(item.getBarcode())!=null) {
    			int quantity =  itemCountMap.get(item.getBarcode());
    			ItemDetail itemDetail = new ItemDetail(item.getBarcode(), item.getName(), item.getPrice(), quantity,calculateSubtotal(item.getPrice(),quantity));
    			allItemDetails.add(itemDetail);
    		}
    	}
    	return allItemDetails;
    }
    
 // P: 1 mins
 // D: 1 mins
 // C: The time used is as expected.
 // A: Keep making achievable planning like this.
    
    private int calculateSubtotal(int price, int quantity) {
    	return price * quantity;
    }

// P: 2 mins
// D: 2 mins
// C: The time used is as expected.
// A: Keep making achievable planning like this.
    private int calculateTotal(List<ItemDetail> allItemDetails) {
    	int total = 0;
    	for(ItemDetail item:allItemDetails) {
    		total += item.getSubtotal();
    	}
    	return total;
    }
    
// P: 2 mins
// D: 2 mins
// C: The time used is as expected.
// A: Keep making achievable planning like this.
       
    
    private String renderReceipt(List<ItemDetail> allItemDetails, int total) {
    	String receipt =  "***<store earning no money>Receipt***\n";
    	for(ItemDetail item:allItemDetails) {
    		receipt += "Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Unit price: " + item.getPrice() + " (yuan), Subtotal: " + item.getSubtotal() + " (yuan)\n";
    	}
    	receipt += "----------------------\n" + "Total: " + total + " (yuan)\n" + "**********************";
    	return receipt;
    }
}
