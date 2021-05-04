package com.acme.domain;

import com.acme.utils.HolidayOrdersNotAllowedException;
import com.acme.utils.MyDate;

public class Order {
    private static double taxRate;
    private MyDate orderDate;
    private double orderAmount = 0.00;
    private String customer;
    private Product product;
    private int quantity;
    private static Rushable rushable;

    static { taxRate = 0.05; }

    public static Rushable getRushable() { return rushable; }

    public static void setRushable(Rushable rushable) {
        Order.rushable = rushable;
    }

    public boolean isPriorityOrder() {
        boolean priorityOrder = false;
        if (rushable != null) {
            priorityOrder = rushable.isRushable(orderDate, orderAmount);
        }
        return priorityOrder;
    }

    public MyDate getOrderDate() { return orderDate; }

    public void setOrderDate(MyDate orderDate)
        throws HolidayOrdersNotAllowedException {
        if (isHoliday(orderDate)) {
            throw new HolidayOrdersNotAllowedException(orderDate);
        } else {
            this.orderDate = orderDate;
        }
    }

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

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

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

    public Order(MyDate d, double amt, String c, Product p, int q) {
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
        try {
            setOrderDate(d);
        } catch (HolidayOrdersNotAllowedException e) {
            System.out.println(
                "The order date for an order cannot be a holiday! Application closing.");
            System.exit(0);
        }
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

    private boolean isHoliday(MyDate proposedDate) {
        boolean result = false;
        for (MyDate holiday : MyDate.getHolidays()) {
            if (holiday.equals(proposedDate)) {
                result = true;
            }
        }
        return result;
    }
}
