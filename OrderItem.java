public class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    public MenuItem getMenuItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

}
