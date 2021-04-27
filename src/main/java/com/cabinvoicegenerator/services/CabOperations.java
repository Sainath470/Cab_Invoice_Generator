package com.cabinvoicegenerator.services;

import java.util.HashMap;
import java.util.Map;

public class CabOperations {
    private static final double FARE_PER_KM = 10;
    private static final double FARE_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;
    public Map<Integer, double[]> cabOperationsMap = new HashMap<>();
    public static Integer userId;

    public CabOperations() {
    }

    public double calculateCostOfRide(double totalRidingDistance, double totalRidingTimeInHours){
        double totalRidingTimeInMin = totalRidingTimeInHours * 60;
      double totalFare = totalRidingDistance * FARE_PER_KM + totalRidingTimeInMin * FARE_PER_MINUTE;

        return Math.max(totalFare, MINIMUM_FARE);
    }

    public double calculateMultipleRideCost(CabRide[] multipleRides){
        double totalRideCost = 0;
        for (CabRide ride :multipleRides) {
            totalRideCost += calculateCostOfRide(ride.rideDistance,ride.timeInMin);
        }
        return totalRideCost;
    }

    public double calculateAverageCostForRides(CabRide[] rides){
        double totalRideCost =  0;
        for(CabRide ride : rides){
            totalRideCost += calculateCostOfRide(ride.rideDistance,ride.timeInMin);
        }
        return totalRideCost / rides.length;
    }

    public Integer getUserId(){
        return userId;
    }

    public double[] getRideDetails(Integer userId){
        return cabOperationsMap.get(userId);
    }

    public int getNumberOfRides(CabRide[] numberOfRides){
        return numberOfRides.length;
    }

    public void addUserRideDetails(CabOperations user, CabRide[] rides){
        double[] userTotalRideDetails = {user.calculateAverageCostForRides(rides),
                                        user.calculateMultipleRideCost(rides),
                                        user.getNumberOfRides(rides)};
        cabOperationsMap.put(user.getUserId(),userTotalRideDetails);
    }


}
