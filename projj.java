import java.util.*;

class MenuItem {
    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - ₹" + price;
    }
}

class OrderItem {
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

class Order {
    private List<OrderItem> orderItems;
    private final double TAX_RATE = 0.07; // 7% tax

    public Order() {
        orderItems = new ArrayList<>();
    }

    public void addItem(MenuItem item, int quantity) {
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
            System.out.println(
                    oi.getMenuItem().getName() + " x" + oi.getQuantity() +
                            " = ₹" + oi.getTotalPrice());
        }
        System.out.println("--------------------");
        System.out.println("Subtotal: ₹" + calculateSubtotal());
        System.out.println("Tax (7%): ₹" + calculateTax());
        System.out.println("Total: ₹" + calculateTotal());
        System.out.println("--------------------");
    }
}

public class projj {

    private static List<MenuItem> menu = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMenu();
        Order order = new Order();

        System.out.println("Welcome to NAOMI Cafe (₹)\n");

        boolean ordering = true;
        while (ordering) {
            printMenu();
            System.out.print("Enter item ID to order (0 to finish): ");
            int id = sc.nextInt();
            if (id == 0) {
                ordering = false;
                break;
            }
            MenuItem item = findMenuItemById(id);
            if (item == null) {
                System.out.println("Invalid item ID. Try again.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            if (qty <= 0) {
                System.out.println("Quantity must be positive. Try again.");
                continue;
            }

            order.addItem(item, qty);
            System.out.println("Added " + qty + " x " + item.getName() + " to order.");
        }

        order.printBill();
        System.out.println("Thank you for visiting!");
    }

    private static void initializeMenu() {
        menu.add(new MenuItem(1, "Burger", 120.0));
        menu.add(new MenuItem(2, "Fries", 80.0));
        menu.add(new MenuItem(3, "Pizza", 250.0));
        menu.add(new MenuItem(4, "Salad", 100.0));
        menu.add(new MenuItem(5, "Soft Drink", 50.0));
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    private static MenuItem findMenuItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
}