package com.mqtt.mqttspring;

import com.mqtt.mqttspring.controller.LocationController;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public abstract class ContractTestBaseClass {

    @Autowired
    protected LocationController locationController;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(locationController);
 //       RestAssureMockMvc.standloneSetup(locationController);
//        Mockito.when(locationController.getLocation("1"))
//                .thenReturn(Or)
    }

}
