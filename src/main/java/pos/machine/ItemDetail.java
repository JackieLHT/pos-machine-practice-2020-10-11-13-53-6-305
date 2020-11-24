package pos.machine;

public class ItemDetail {
    private final String barcode;
    private final String name;
    private final int price;
    private int quantity;
    private int subtotal;
    
    public ItemDetail(String barcode, String name, int price,int quantity,int subtotal) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }
}
