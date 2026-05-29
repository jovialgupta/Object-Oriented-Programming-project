import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurauntBillingSystem {
    private static List<MenuItem> menu = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMenu();
        Order order = new Order();

        System.out.println("Welcome to OOP Restaurant Billing System\n");

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
        menu.add(new MenuItem(1, "Burger", 5.99));
        menu.add(new MenuItem(2, "Fries", 2.99));
        menu.add(new MenuItem(3, "Pizza", 8.99));
        menu.add(new MenuItem(4, "Salad", 4.99));
        menu.add(new MenuItem(5, "Soft Drink", 1.99));
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
