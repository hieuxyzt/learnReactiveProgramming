package com.example.webflux.sec05.assignment;

import com.example.webflux.courseUtil.Util;
import lombok.Getter;

@Getter
public class PurchaseOrder {
    private String item;
    private int quantity;
    private double price;
    private String category;

    public PurchaseOrder(){
        this.item = Util.faker().commerce().productName();
        this.price = Double.parseDouble(Util.faker().commerce().price());
        this.category = Util.faker().commerce().department();
        this.quantity = Util.faker().random().nextInt(1, 10);
    }
}
