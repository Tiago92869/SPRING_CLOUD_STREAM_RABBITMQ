package com.classservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SenderService {

    @Autowired
    private StreamBridge streamBridge;

    public void sendSchool(){

        String hello  = "Hello";

        streamBridge.send("schoolSender", hello);
    }

}
