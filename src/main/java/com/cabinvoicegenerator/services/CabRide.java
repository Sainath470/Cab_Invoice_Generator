package com.cabinvoicegenerator.services;

public class CabRide {
    public final double rideDistance;
    public double timeInMin;

    public CabRide(double rideDistance, double timeInHrs) {
        this.rideDistance = rideDistance;
        this.timeInMin = timeInHrs * 60;
    }
}
