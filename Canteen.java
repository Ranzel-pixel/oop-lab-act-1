import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Menu
        String[] foodItems = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] prices = {80.00, 120.00, 100.00, 70.00, 90.00};

        // Overall transaction totals
        int totalItems = 0;
        double totalBeforeDiscount = 0.00;
        double totalDiscount = 0.00;

        String orderAgain = "Y";

        // Display menu
        System.out.println("===== MENU =====");

        for (int i = 0; i < foodItems.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n",
                    i + 1, foodItems[i], prices[i]);
        }

        // Ordering
        while (orderAgain.equalsIgnoreCase("Y")) {

            System.out.print("\nEnter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            // Check if valid
            if (itemNumber < 1 || itemNumber > foodItems.length
                    || quantity < 1 || quantity > 10) {

                System.out.println(
                        "\nInvalid order! Please enter a valid item and quantity."
                );

                System.out.print("\nDo you want to order again? (Y/N): ");
                orderAgain = input.next();

                continue;
            }

            // Ask if student
            System.out.print("Are you a student? (Y/N): ");
            String student = input.next();

            // Calculate subtotal
            double subtotal = prices[itemNumber - 1] * quantity;

            // Determine discount
            double discountRate;

            if (student.equalsIgnoreCase("Y") && subtotal >= 500) {
                discountRate = 0.15;
            } else if (student.equalsIgnoreCase("Y")) {
                discountRate = 0.10;
            } else if (subtotal >= 500) {
                discountRate = 0.05;
            } else {
                discountRate = 0.00;
            }

            // Calculate discount and order total
            double discount = subtotal * discountRate;
            double orderTotal = subtotal - discount;

            // Display order details
            System.out.printf("%nSubtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discount);
            System.out.printf("Order total: $%.2f%n", orderTotal);

            // Add valid order to overall totals
            totalItems += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            // Ask if customer wants another order
            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next();
        }

        // Final computation
        double finalAmount = totalBeforeDiscount - totalDiscount;

        // Display final summary
        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalItems);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        input.close();
    }
}