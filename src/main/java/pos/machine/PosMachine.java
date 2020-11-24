package pos.machine;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class PosMachine {
	
    public String printReceipt(List<String> barcodes) {
    	List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();
    	HashMap<String,Integer> itemCountMap = getItemCountMap(barcodes);
    	
    	List<ItemDetail> allItemDetails = getItemDetails(itemInfos,itemCountMap);
    	
    	int total = calculateTotal(allItemDetails);
    	
    	
        return renderReceipt(allItemDetails,total);
    }
    
    private HashMap<String,Integer> getItemCountMap(List<String> barcodes) {
    	HashMap<String, Integer> itemCountMap = new HashMap<String, Integer>();
    	for(String item:barcodes) {
    		if(!itemCountMap.containsKey(item))
    			itemCountMap.put(item, 1);
    		else itemCountMap.put(item, itemCountMap.get(item) + 1);
    	}
    	return itemCountMap;
    }
    
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
    
    private int calculateSubtotal(int price, int quantity) {
    	return price * quantity;
    }
    
    private int calculateTotal(List<ItemDetail> allItemDetails) {
    	int total = 0;
    	for(ItemDetail item:allItemDetails) {
    		total += item.getSubtotal();
    	}
    	return total;
    }
    
    private String renderReceipt(List<ItemDetail> allItemDetails, int total) {
    	String receipt =  "***<store earning no money>Receipt***\n";
    	for(ItemDetail item:allItemDetails) {
    		receipt += "Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Unit price: " + item.getPrice() + " (yuan), Subtotal: " + item.getSubtotal() + " (yuan)\n";
    	}
    	receipt += "----------------------\n" + "Total: " + total + " (yuan)\n" + "**********************";
    	return receipt;
    }
}
