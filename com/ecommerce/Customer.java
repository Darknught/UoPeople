package com.ecommerce;


import java.util.ArrayList;
import java.util.List;
import com.ecommerce.orders.Order;


public class Customer {
private int customerID;
private String name;
private List<Product> cart;


public Customer(int customerID, String name) {
this.customerID = customerID;
this.name = name;
this.cart = new ArrayList<>();
}


public void addToCart(Product product) {
cart.add(product);
}


public void removeFromCart(Product product) {
cart.remove(product);
}


public void displayCart() {
if (cart.isEmpty()) {
System.out.println("Cart is empty");
} else {
for (Product p : cart) {
System.out.println(p);
}
}
}


public double calculateTotal() {
double total = 0;
for (Product p : cart) {
total += p.getPrice();
}
return total;
}


public Order placeOrder(int orderID) {
return new Order(orderID, this, new ArrayList<>(cart));
}


@Override
public String toString() {
return "Customer ID: " + customerID + ", Name: " + name;
}
}
