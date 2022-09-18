package com.example.webflux.sec04.helper;

import com.example.webflux.courseUtil.Util;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PurchaseOrder {
    private String item;
    private String price;
    private int userId;

    public PurchaseOrder(int userId) {
        this.userId = userId;
        this.item = Util.faker().commerce().productName();
        this.price = Util.faker().commerce().price();
    }
}
