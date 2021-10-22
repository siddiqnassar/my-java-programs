package com.locationsimulator.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.locationsimulator.service.LocationSimulatorService;


@RestController
public class LocationSimulatorController {

    @Autowired
    private LocationSimulatorService locationSimulatorService;

	private static final Logger LOGGER = Logger.getLogger(LocationSimulatorController.class.getName());


   @GetMapping(value = "/get")
    public String getLocationResults(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
       LOGGER.log(Level.INFO, "The destination is {0}", destination);
        return locationSimulatorService.getlocationResults(origin, destination);
    }
}
