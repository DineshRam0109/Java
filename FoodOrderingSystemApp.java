import java.io.*;
import java.util.*;

class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String a, String b, double c) {
        name = a;
        description = b;
        price = c;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println("Item: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
    }

    public double calculatePrice() {
        return price;
    }
}

class SpecialMenuItem extends MenuItem {
    private String menuOfTheDay;
    private double discount;

    public SpecialMenuItem(String a, String b, double c, String d, double e) {
        super(a, b, c);
        menuOfTheDay = d;
        discount = e;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Menu of the Day: " + menuOfTheDay);
        System.out.println("Discount: " + discount + "%");
    }

    @Override
    public double calculatePrice() {
        return getPrice() - getPrice() * discount / 100;
    }
}

public class FoodOrderingSystem {
    static final int MAX_ITEMS = 10;
    static MenuItem[] menuItems = new MenuItem[MAX_ITEMS];
    static MenuItem[] order = new MenuItem[MAX_ITEMS];
    static int[] orderQuantities = new int[MAX_ITEMS];
    static int orderCount = 0;

    public static void main(String[] args) throws IOException {
        int menuItemCount = loadMenuItems();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---- Menu ----");
            displayMenu(menuItemCount);
            System.out.println("\n1. Start New Order");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter customer name: ");
                sc.nextLine();
                String customerName = sc.nextLine();
                processOrder(sc, menuItemCount);
                checkout(customerName);
                resetOrder(); // Reset for next customer
            } else if (choice != 2) {
                System.out.println("Invalid choice.");
            }
        } while (choice != 2);

        System.out.println("****** Thank you for using the Food Ordering System! ******");
        sc.close();
    }

    private static void processOrder(Scanner sc, int menuItemCount) {
        int choice;

        do {
            System.out.println("\n1. Add item to order");
            System.out.println("2. View order");
            System.out.println("3. Checkout");
            System.out.println("4. Cancel Order and Start New");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter item number to add to order: ");
                    int itemNumber = sc.nextInt();
                    if (itemNumber > 0 && itemNumber <= menuItemCount) {
                        System.out.print("Enter quantity: ");
                        int quantity = sc.nextInt();
                        if (quantity > 0) {
                            addItemToOrder(menuItems[itemNumber - 1], quantity);
                            System.out.println("Item added to order.");
                        } else {
                            System.out.println("Quantity must be greater than 0.");
                        }
                    } else {
                        System.out.println("Invalid item number.");
                    }
                    break;
                case 2:
                    displayOrder();
                    break;
                case 3:
                    return; // Proceed to checkout
                case 4:
                    resetOrder(); // Clear the order and start fresh
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    private static void addItemToOrder(MenuItem item, int quantity) {
        boolean found = false;

        for (int i = 0; i < orderCount; i++) {
            if (order[i].getName().equals(item.getName())) {
                orderQuantities[i] += quantity;
                found = true;
                break;
            }
        }

        if (!found && orderCount < MAX_ITEMS) {
            order[orderCount] = item;
            orderQuantities[orderCount] = quantity;
            orderCount++;
        }
    }

    private static void displayMenu(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + ". ");
            menuItems[i].displayInfo();
            System.out.println();
        }
    }

    private static void displayOrder() {
        if (orderCount == 0) {
            System.out.println("Your order is empty.");
        } else {
            System.out.println("\nYour Order:");
            for (int i = 0; i < orderCount; i++) {
                order[i].displayInfo();
                System.out.println("Quantity: " + orderQuantities[i]);
                System.out.println();
            }
        }
    }

    private static int loadMenuItems() throws IOException {
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            while ((line = br.readLine()) != null && count < MAX_ITEMS) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    menuItems[count++] = new MenuItem(data[0], data[1], Double.parseDouble(data[2]));
                } else if (data.length == 5) {
                    menuItems[count++] = new SpecialMenuItem(data[0], data[1], Double.parseDouble(data[2]), data[3], Double.parseDouble(data[4]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Menu file not found, starting with empty menu.");
        }

        return count;
    }

    private static void checkout(String customerName) throws IOException {
        double total = 0.0;
        FileWriter billFile = new FileWriter("bill.txt", true);

        // Write the customer name and order details
        billFile.write("\n---- " + customerName + "'s Order Bill ----\n");
        for (int i = 0; i < orderCount; i++) {
            double price = order[i].calculatePrice() * orderQuantities[i];
            total += price;
            billFile.write(order[i].getName() + " x " + orderQuantities[i] + " - $" + price + "\n");
        }

        billFile.write("Total: $" + total + "\n");
        billFile.write("\n****** Thank you for using the Food Ordering System! ******\n");
        billFile.close();
        System.out.println("Checkout complete! Bill for " + customerName + " has been saved to 'bill.txt'.");
    }

    private static void resetOrder() {
        orderCount = 0;
        Arrays.fill(orderQuantities, 0);
        Arrays.fill(order, null);
    }
}
