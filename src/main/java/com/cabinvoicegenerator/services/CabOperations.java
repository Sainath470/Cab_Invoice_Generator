package com.cabinvoicegenerator.services;

public class CabOperations {
    private static final double FARE_PER_KM = 10;
    private static final double FARE_PER_MINUTE = 1;

    public double calculateCostOfRide(double totalRidingDistance, double totalRidingTimeInHours){
        double totalRidingTimeInMin = totalRidingTimeInHours * 60;
        return totalRidingDistance * FARE_PER_KM + totalRidingTimeInMin * FARE_PER_MINUTE;
    }
}
