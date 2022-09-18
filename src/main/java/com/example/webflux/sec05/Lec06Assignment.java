package com.example.webflux.sec05;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec05.assignment.InventoryService;
import com.example.webflux.sec05.assignment.OrderService;
import com.example.webflux.sec05.assignment.RevenueService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec06Assignment {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        // revenue, inventory observe(listen) order stream
        orderService.getFlux().subscribe(revenueService.subscribeOrderStream());
        orderService.getFlux().subscribe(inventoryService.subscribeOrderStream());

        // scan db send to custom
//        revenueService.revenueStream().subscribe(Util.subscriber());
//        inventoryService.inventoryStream().subscribe(Util.subscriber());
        revenueService.revenueStream().subscribe(s -> log.info("revenue: {}", s));
        inventoryService.inventoryStream().subscribe(s -> log.info("inventory: {}", s));

        Util.sleepSeconds(20);
    }
}
