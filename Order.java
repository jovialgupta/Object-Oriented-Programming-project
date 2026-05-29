import java.util.*;

public class Order {
    private List<OrderItem> orderItems;
    private final double TAX_RATE = 0.07; // 7% tax

    public Order() {
        orderItems = new ArrayList<>();
    }

    public void addItem(MenuItem item, int quantity) {
        // If already added, increase quantity
        for (OrderItem oi : orderItems) {
            if (oi.getMenuItem().getId() == item.getId()) {
                int newQty = oi.getQuantity() + quantity;
                orderItems.remove(oi);
                orderItems.add(new OrderItem(item, newQty));
                return;
            }
        }
        orderItems.add(new OrderItem(item, quantity));
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (OrderItem oi : orderItems) {
            subtotal += oi.getTotalPrice();
        }
        return subtotal;
    }

    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }

    public void printBill() {
        System.out.println("\n----- BILL -----");
        for (OrderItem oi : orderItems) {
            System.out.printf("%-20s x%d  $%.2f\n",
                    oi.getMenuItem().getName(), oi.getQuantity(), oi.getTotalPrice());
        }
        System.out.printf("Subtotal: $%.2f\n", calculateSubtotal());
        System.out.printf("Tax (7%%): $%.2f\n", calculateTax());
        System.out.printf("Total: $%.2f\n", calculateTotal());
        System.out.println("----------------");
    }

}
