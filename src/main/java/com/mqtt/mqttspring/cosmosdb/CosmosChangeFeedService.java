package com.mqtt.mqttspring.cosmosdb;

import com.azure.cosmos.ChangeFeedProcessorBuilder;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.ChangeFeedProcessor;
import com.azure.cosmos.models.ChangeFeedProcessorOptions;
import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CosmosChangeFeedService {

    @Autowired
    private CosmosAsyncContainer cosmosAsyncContainer;

    @Autowired
    private CosmosAsyncContainer leaseAsyncContainer; // Ensure this is an async container for leases

    @Autowired
    private MqttClient mqttClient;

    @Value("${mqtt.topic}")
    private String mqttTopic;

    @PostConstruct
    public void startChangeFeedProcessor() {
        ChangeFeedProcessor changeFeedProcessor = new ChangeFeedProcessorBuilder()
                .hostName("hostName")
                .feedContainer(cosmosAsyncContainer)
                .leaseContainer(leaseAsyncContainer)
                .handleChanges((List<JsonNode> docs) -> {
                    docs.forEach(doc -> {
                        try {
                            String message = doc.toString();
                            mqttClient.publish(mqttTopic, new MqttMessage(message.getBytes()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                })
                .options(new ChangeFeedProcessorOptions())
                .buildChangeFeedProcessor();

        changeFeedProcessor.start().subscribe();
    }
}
