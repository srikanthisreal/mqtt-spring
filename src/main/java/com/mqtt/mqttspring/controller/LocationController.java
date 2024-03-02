package com.mqtt.mqttspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final Map<Long, Location> locations = new HashMap<>();

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        // In a real application, you should save the location to the database
        // Here, we're just storing it in a map for simplicity
        locations.put(location.id(), location);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        Location location = locations.get(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location locationUpdate) {
        Location existingLocation = locations.get(id);
        if (existingLocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // In a real application, you would update the location in the database
        // Here, we're just updating the map entry fo
        Location updatedLocation = new Location(id, locationUpdate.name(), locationUpdate.description());
        locations.put(id, updatedLocation);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }
}