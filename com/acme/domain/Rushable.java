package com.acme.domain;

import com.acme.utils.*;

public interface Rushable {
    public abstract boolean isRushable(MyDate orderDate, double amount);
}
