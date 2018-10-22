package com.kozyrenko.demo.gisjavasql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ride {
    @JsonIgnore private Point startGeom;
    @JsonIgnore private Point endGeom;
    @Id private Long id;
    private float distanceTravelled;
    private double endLocationLat;
    private double endLocationLong;
    private float driverRating;
    private float riderRating;
    private int activeDriver;
    private String requestedCarCategory;
    private float freeCreditUsed;
    private double startLocationLong;
    private double startLocationLat;
    private int riderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(double endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public double getEndLocationLong() {
        return endLocationLong;
    }

    public void setEndLocationLong(double endLocationLong) {
        this.endLocationLong = endLocationLong;
    }

    public float getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(float driverRating) {
        this.driverRating = driverRating;
    }

    public float getRiderRating() {
        return riderRating;
    }

    public void setRiderRating(float riderRating) {
        this.riderRating = riderRating;
    }

    public int getActiveDriver() {
        return activeDriver;
    }

    public void setActiveDriver(int activeDriver) {
        this.activeDriver = activeDriver;
    }

    public String getRequestedCarCategory() {
        return requestedCarCategory;
    }

    public void setRequestedCarCategory(String requestedCarCategory) {
        this.requestedCarCategory = requestedCarCategory;
    }

    public float getFreeCreditUsed() {
        return freeCreditUsed;
    }

    public void setFreeCreditUsed(float freeCreditUsed) {
        this.freeCreditUsed = freeCreditUsed;
    }

    public double getStartLocationLong() {
        return startLocationLong;
    }

    public void setStartLocationLong(double startLocationLong) {
        this.startLocationLong = startLocationLong;
    }

    public double getStartLocationLat() {
        return startLocationLat;
    }

    public void setStartLocationLat(double startLocationLat) {
        this.startLocationLat = startLocationLat;
    }

    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    public Point getStartGeom() {
        return startGeom;
    }

    public void setStartGeom(Point startGeom) {
        this.startGeom = startGeom;
    }

    public Point getEndGeom() {
        return endGeom;
    }

    public void setEndGeom(Point endGeom) {
        this.endGeom = endGeom;
    }

    public float getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(float distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
