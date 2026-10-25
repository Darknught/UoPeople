// File: Main.java

import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product laptop = new Product(1, "Laptop", 1200.00);
        Product phone = new Product(2, "Smartphone", 800.00);
        Product headphones = new Product(3, "Headphones", 150.00);

        // Display available products
        System.out.println("=== Available Products ===");
        System.out.println(laptop);
        System.out.println(phone);
        System.out.println(headphones);

        // Create a customer
        Customer customer1 = new Customer(101, "Hassan");
        System.out.println("\n=== Customer Information ===");
        System.out.println(customer1);

        // Customer adds products to cart
        customer1.addToCart(laptop);
        customer1.addToCart(headphones);

        // Display shopping cart and total
        System.out.println("\n=== Shopping Cart ===");
        customer1.displayCart();
        System.out.println("Total: $" + customer1.calculateTotal());

        // Place an order
        Order order1 = customer1.placeOrder(1001);

        // Display order summary
        System.out.println("\n=== Order Summary ===");
        System.out.println(order1.generateSummary());

        // Update order status
        order1.updateStatus("Shipped");
        System.out.println("Updated Order Status: " + order1.getStatus());
    }
}

