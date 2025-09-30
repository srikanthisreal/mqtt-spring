package com.mqtt.mqttspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqttSpringApplication {

    private static final Logger log = LoggerFactory.getLogger(MqttSpringApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MqttSpringApplication.class, args);
        log.info("MqttSpringApplication started successfully.");
    }
}
