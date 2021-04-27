package com.cabinvoicegenerator.services;

public class CabOperations {
    private static final double FARE_PER_KM = 10;
    private static final double FARE_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    public double calculateCostOfRide(double totalRidingDistance, double totalRidingTimeInHours){
        double totalRidingTimeInMin = totalRidingTimeInHours * 60;
      double totalFare = totalRidingDistance * FARE_PER_KM + totalRidingTimeInMin * FARE_PER_MINUTE;

        return Math.max(totalFare, MINIMUM_FARE);
    }
}
