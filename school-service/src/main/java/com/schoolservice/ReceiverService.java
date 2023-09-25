package com.schoolservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ReceiverService {

    private final SchoolService schoolService;

    @Autowired
    public ReceiverService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Bean
    public Consumer<String> schoolReceiver(){

        return s -> {
            System.out.println("FUNCIONA!!!");
        };

    }
}
