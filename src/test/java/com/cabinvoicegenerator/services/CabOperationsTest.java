package com.cabinvoicegenerator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CabOperationsTest {
    CabOperations cabOperations = new CabOperations();

    @Test
    public void givenRidingDetails_WhenGiven_ShouldReturnFare(){
        double taxiDistance = 25.0;
        double taxiTime = 3.0;
        double totalFare = cabOperations.calculateCostOfRide(taxiDistance, taxiTime);
        Assertions.assertEquals(430, totalFare);
    }

}