package com.kozyrenko.demo.gisjavasql.service;

import com.kozyrenko.demo.gisjavasql.model.Ride;
import com.kozyrenko.demo.gisjavasql.repository.RideRepository;
import com.kozyrenko.demo.gisjavasql.util.TransformHelper;
import com.vividsolutions.jts.geom.Point;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class RideService {

    private static final Logger LOG = LoggerFactory.getLogger(RideService.class);
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Autowired
    private RideRepository rideRepo;

    public List<Ride> findByStartLocation(double lat, double lon, int radius) {
        List<Ride> result = Collections.emptyList();
        try {
            Point center = TransformHelper.getInstance().transform(lat, lon);
            result = rideRepo.findByStartLocation(center, (float) (radius / TransformHelper.AUSTIN_DISTORTION));
        } catch (TransformException e) {
            LOG.error("Could not transform " + lat + ", " + lon + " to geom!");
        }
        return result;
    }

    public List<Ride> findByEndLocation(double lat, double lon, int radius) {
        List<Ride> result = Collections.emptyList();
        try {
            Point center = TransformHelper.getInstance().transform(lat, lon);
            result = rideRepo.findByEndLocation(center, (float) (radius / TransformHelper.AUSTIN_DISTORTION));
        } catch (TransformException e) {
            LOG.error("Could not transform " + lat + ", " + lon + " to geom!");
        }
        return result;
    }
}