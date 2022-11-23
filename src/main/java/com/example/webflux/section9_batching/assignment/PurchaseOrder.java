package com.example.webflux.section9_batching.assignment;

import com.example.webflux.courseUtil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {
    private String item;
    private double price;
    private String category;

    public PurchaseOrder() {
        item = Util.faker().commerce().productName();
        price = Double.parseDouble(Util.faker().commerce().price());
        category = Util.faker().commerce().department();
    }
}
