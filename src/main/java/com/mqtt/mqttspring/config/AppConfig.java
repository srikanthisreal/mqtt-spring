package com.mqtt.mqttspring.config;

import com.azure.cosmos.*;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${azure.cosmos.uri}")
    private String cosmosUri;

    @Value("${azure.cosmos.key}")
    private String cosmosKey;

    @Value("${azure.cosmos.database}")
    private String databaseName;

    @Value("${azure.cosmos.container}")
    private String containerName;

    @Value("${mqtt.broker.url}")
    private String mqttBrokerUrl;

    @Value("${mqtt.client.id}")
    private String mqttClientId;

    @Value("${mqtt.topic}")
    private String mqttTopic;

    @Bean
    public CosmosAsyncClient cosmosAsyncClient() {
        return new CosmosClientBuilder()
                .endpoint(cosmosUri)
                .key(cosmosKey)
                .buildAsyncClient();
    }

    @Bean
    public CosmosAsyncDatabase cosmosAsyncDatabase() {
        return cosmosAsyncClient().getDatabase(databaseName);
    }

    @Bean
    public CosmosAsyncContainer cosmosAsyncContainer() {
        return cosmosAsyncDatabase().getContainer(containerName);
    }

    @Bean
    public CosmosAsyncContainer leaseAsyncContainer() {
        return cosmosAsyncDatabase().getContainer(containerName+"-lease");
    }

    @Bean
    public MqttClient mqttClient() throws Exception {
        MqttClient client = new MqttClient(mqttBrokerUrl, mqttClientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        client.connect(options);
        return client;
    }
}
