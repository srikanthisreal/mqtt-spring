package com.mqtt.mqttspring.contracts

Contract.make {
    request {
        method 'POST'
        url '/location'
        body([
                id: 123,
                name: "Test Location"
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
    }
}
