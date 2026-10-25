package com.ecommerce.orders;


import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.util.List;


public class Order {
private int orderID;
private Customer customer;
private List<Product> products;
private double total;
private String status;


public Order(int orderID, Customer customer, List<Product> products) {
this.orderID = orderID;
this.customer = customer;
this.products = products;
this.total = calculateTotal();
this.status = "Pending";
}


private double calculateTotal() {
double sum = 0;
for (Product p : products) {
sum += p.getPrice();
}
return sum;
}


public String generateSummary() {
StringBuilder sb = new StringBuilder();
sb.append("Order ID: ").append(orderID).append("\n");
sb.append("Customer: ").append(customer).append("\n");
sb.append("Products: ").append("\n");
for (Product p : products) {
sb.append(" - ").append(p).append("\n");
}
sb.append("Total: $").append(total).append("\n");
sb.append("Status: ").append(status);
return sb.toString();
}


public void updateStatus(String status) {
this.status = status;
}


public String getStatus() {
return status;
}
}
