package com.example.webflux.sec04;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.sec04.helper.OrderSerivce;
import com.example.webflux.sec04.helper.UserService;

public class Lec12FlatMap {

    public static void main(String[] args) {
        UserService.getUsers()
                .concatMap(user -> OrderSerivce.getOrders(user.getUserId()))
                .log()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
        /**
         *  Flatmap tao nhieu thread
         *  5 OrderService 5 Thread
         *  flatMap pass items to Subscriber dùng nhiều Thread
         *  -> Thời gian là thời gian OrderService xử lý lâu nhất
         *
         *
         *  ConcatMap: đợi xử lý xong 1 order mới xử lý order mới
         */
    }
}
