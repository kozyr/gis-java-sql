package com.kozyrenko.demo.gisjavasql.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.kozyrenko.demo.gisjavasql.model.Ride;
import com.kozyrenko.demo.gisjavasql.service.RideService;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
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

    @GetMapping(value = "/nearStart",
            params = {"lat", "lon", "radius"},
            produces = "application/json")
    public FeatureCollection nearStart(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("radius") int radius) {
        List<Ride> rides = rideService.findByStartLocation(lat, lon, radius);
        FeatureCollection resultCollection = new FeatureCollection();

        for (Ride ride : rides) {
            Feature feature = fromRide(ride);
            resultCollection.add(feature);
        }

        return resultCollection;
    }

    private Feature fromRide(Ride ride) {
        Feature feature = new Feature();
        feature.setId(String.valueOf(ride.getId()));
        feature.setGeometry(new Point(ride.getStartLocationLong(), ride.getStartLocationLat()));
        feature.setProperty("driverRating", ride.getDriverRating());
        feature.setProperty("riderRating", ride.getRiderRating());

        return feature;
    }


    @GetMapping(value = "/nearEnd",
            params = {"lat", "lon", "radius"},
            produces = "application/json")
    public JsonNode nearEnd(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("radius") int radius) {
        return rideService.findByEndLocation(lat, lon, radius);
    }

}
