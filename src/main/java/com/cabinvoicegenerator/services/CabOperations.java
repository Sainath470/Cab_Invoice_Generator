package com.cabinvoicegenerator.services;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CabOperations {
    private static final double FARE_PER_KM = 10;
    private static final double FARE_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5.0;

    private static final double PREMIUM_FARE_PER_KM = 15;
    private static final double PREMIUM_FARE_PER_MINUTE = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;

    public Map<Integer, double[]> cabOperationsMap = new HashMap<>();
    public Integer userId;
    public String typeOfTheCustomer;


    public CabOperations(String  customerType){
     this.typeOfTheCustomer = customerType;
    }

    public CabOperations(Integer userId, String customerType) {
        this.userId = userId;
        this.typeOfTheCustomer = customerType;
        cabOperationsMap.put(userId,null);
    }



    public double calculateTotalRideCost(double totalRidingDistance, double totalRidingTimeInHours) {

        if (this.typeOfTheCustomer.toLowerCase(Locale.ROOT).startsWith("r")) {
            double totalRidingTimeInMin = totalRidingTimeInHours * 60;
            double totalFare = totalRidingDistance * FARE_PER_KM + totalRidingTimeInMin * FARE_PER_MINUTE;

            return Math.max(totalFare, MINIMUM_FARE);
        }
        if (this.typeOfTheCustomer.toLowerCase(Locale.ROOT).startsWith("p")) {
            double totalRidingTimeInMin = totalRidingTimeInHours * 60;
            double totalFare = totalRidingDistance * PREMIUM_FARE_PER_KM + totalRidingTimeInMin * PREMIUM_FARE_PER_MINUTE;

            return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
        }
        else
            return 0.0;
    }

    public double calculateTotalRideCost(CabRide[] multipleRides){
        double totalRideCost = 0;
        for (CabRide ride :multipleRides) {
            totalRideCost += calculateTotalRideCost(ride.rideDistance,ride.timeInMin);
        }
        return totalRideCost;
    }

    public double calculateAverageCostForRides(CabRide[] rides){
        double totalRideCost =  0;
        for(CabRide ride : rides){
            totalRideCost += calculateTotalRideCost(ride.rideDistance,ride.timeInMin);
        }
        return totalRideCost / rides.length;
    }

    public Integer getUserId(){
        return userId;
    }

    public String getTypeOfTheCustomer(Integer userId){
        return typeOfTheCustomer;
    }

    public double[] getRideDetails(Integer userId){
        return cabOperationsMap.get(userId);
    }

    public int getNumberOfRides(CabRide[] numberOfRides){
        return numberOfRides.length;
    }

    public void addUserRideDetails(CabOperations user, CabRide[] rides){
        double[] userTotalRideDetails = {user.calculateAverageCostForRides(rides),
                                        user.calculateTotalRideCost(rides),
                                        user.getNumberOfRides(rides)};
        cabOperationsMap.put(user.getUserId(),userTotalRideDetails);
    }


}
