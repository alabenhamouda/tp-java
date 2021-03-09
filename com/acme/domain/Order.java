package com.acme.domain;

import com.acme.utils.MyDate;

public class Order {
    private static double taxRate;
    private MyDate orderDate;
    private double orderAmount = 0.00;
    private String customer;
    private Good product;
    private int quantity;

    static { taxRate = 0.05; }

    public MyDate getOrderDate() { return orderDate; }

    public void setOrderDate(MyDate orderDate) { this.orderDate = orderDate; }

    public double getOrderAmount() { return orderAmount; }

    public void setOrderAmount(double orderAmount) {
        if (orderAmount <= 0) {
            System.out.println(
                "Attempting to set the orderAmount to a value less than or equal to zero");
        } else
            this.orderAmount = orderAmount;
    }

    public String getCustomer() { return customer; }

    public void setCustomer(String customer) { this.customer = customer; }

    public Good getProduct() { return product; }

    public void setProduct(Good product) { this.product = product; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println(
                "Attempting to set the quantity to a value less than or equal to zero");
        } else
            this.quantity = quantity;
    }
    public static void setTaxRate(double newRate) { taxRate = newRate; }
    public static double getTaxRate() { return taxRate; }

    public Order(MyDate d, double amt, String c, Good p, int q) {
        if (amt <= 0) {
            System.out.println(
                "Attempting to set the orderAmount to a value less than or equal to zero");
            return;
        }
        if (q <= 0) {
            System.out.println(
                "Attempting to set the quantity to a value less than or equal to zero");
            return;
        }
        orderDate = d;
        orderAmount = amt;
        customer = c;
        product = p;
        quantity = q;
    }

    // public Order(MyDate d, double amt, String c) {
    //     this(d, amt, c, new Good("Anvil", 1, 1), 1);
    // }

    public String toString() {
        return quantity + " ea. " + product + " for " + customer;
    }

    public static void computeTaxOn(double anAmount) {
        System.out.println("The tax for " + anAmount +
                           " is: " + anAmount * Order.taxRate);
    }

    public double computeTax() {
        System.out.println("The tax for this order is: " +
                           orderAmount * Order.taxRate);
        return orderAmount * Order.taxRate;
    }

    public char jobSize() {
        if (quantity <= 25) {
            return 'S';
        } else if (quantity <= 75) {
            return 'M';
        } else if (quantity <= 150) {
            return 'L';
        } else {
            return 'X';
        }
    }

    public double computeTotal() {
        double total = orderAmount;
        switch (jobSize()) {
        case 'M':
            total -= 0.01 * orderAmount;
            break;
        case 'L':
            total -= 0.02 * orderAmount;
            break;
        case 'X':
            total -= 0.03 * orderAmount;
            break;
        }
        if (orderAmount <= 1500)
            total += computeTax();
        return total;
    }
}
