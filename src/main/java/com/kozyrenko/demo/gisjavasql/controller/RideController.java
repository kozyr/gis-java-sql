package com.kozyrenko.demo.gisjavasql.controller;
import com.kozyrenko.demo.gisjavasql.model.Ride;
import com.kozyrenko.demo.gisjavasql.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RideController {

    @Autowired
    private RideService rideService;


    @GetMapping("/")
    public String index() {
        return "GIS Demo";
    }

    @GetMapping(value = "/startNear",
            params = {"lat", "lon", "radius"},
            produces = "application/json")
    public List<Ride> startNear(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("radius") int radius) {
        return rideService.findByStartLocation(lat, lon, radius);
    }

    @GetMapping(value = "/endNear",
            params = {"lat", "lon", "radius"},
            produces = "application/json")
    public List<Ride> endNear(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("radius") int radius) {
        return rideService.findByEndLocation(lat, lon, radius);
    }

}
