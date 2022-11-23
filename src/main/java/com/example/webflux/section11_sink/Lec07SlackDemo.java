package com.example.webflux.section11_sink;

import com.example.webflux.courseUtil.Util;
import com.example.webflux.section11_sink.assignment.SlackMember;
import com.example.webflux.section11_sink.assignment.SlackRoom;

public class Lec07SlackDemo {
    public static void main(String[] args) {
        SlackRoom slackRoom = new SlackRoom("reactor");

        SlackMember sam = new SlackMember("sam");
        SlackMember jake = new SlackMember("jake");
        SlackMember mike = new SlackMember("mike");

        slackRoom.joinRoom(sam);
        slackRoom.joinRoom(jake);

        sam.says("Hi all ...");
        Util.sleepSeconds(4);

        jake.says("Hey!");
        sam.says("I simply wanted to say Hi!!...");
        Util.sleepSeconds(4);

        slackRoom.joinRoom(mike);
        mike.says("Hi guys, glad to be here");
        Util.sleepSeconds(4);
    }
}
