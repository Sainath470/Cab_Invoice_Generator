package com.cabinvoicegenerator.services;

public class CabRide {
    public final double RideDistance;
    public double timeInMin;

    public CabRide(double rideDistance, double timeInHrs) {
        RideDistance = rideDistance;
        this.timeInMin = timeInHrs * 60;
    }
}
