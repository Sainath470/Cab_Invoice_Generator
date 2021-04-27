package com.cabinvoicegenerator.services;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CabOperationsTest {

    @Test
    public void givenRidingDetails_WhenGiven_ShouldReturnFare(){
        CabOperations cabOperations = new CabOperations();
        double taxiDistance = 25.0;
        double taxiTime = 3.0;
        double totalFare = cabOperations.calculateCostOfRide(taxiDistance, taxiTime);
        Assertions.assertEquals(430, totalFare);
    }

    @Test
    public void givenMultipleRidingDetails_ShouldReturnTotalAggregateFare(){
        CabOperations cabOperations = new CabOperations();
        CabRide[] multipleRides = {(new CabRide(25,4))
                                        ,(new CabRide(50.0,1.5))
                                        ,(new CabRide(60.0,2.5))};
        double totalCabFareCost = cabOperations.calculateMultipleRideCost(multipleRides);
        Assertions.assertEquals(30150,totalCabFareCost);
    }

}