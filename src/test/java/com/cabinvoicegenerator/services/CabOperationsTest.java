package com.cabinvoicegenerator.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CabOperationsTest {

    @Test
    public void givenRidingDetails_WhenGiven_ShouldReturnFare(){
        CabOperations cabOperations = new CabOperations("R");
        double taxiDistance = 25.0;
        double taxiTime = 3.0;
        double totalFare = cabOperations.calculateTotalRideCost(taxiDistance, taxiTime);
        Assertions.assertEquals(430, totalFare);
    }

    @Test
    public void givenMultipleRidingDetails_ShouldReturnTotalAggregateFare(){
        CabOperations cabOperations = new CabOperations("R");
        CabRide[] multipleRides = {(new CabRide(25,4))
                                        ,(new CabRide(50.0,1.5))
                                        ,(new CabRide(60.0,2.5))};
        double totalCabFareCost = cabOperations.calculateTotalRideCost(multipleRides);
        Assertions.assertEquals(30150,totalCabFareCost);
    }

    @Test
    public void givenMultipleRidingDetails_ShouldReturnLengthAnd_Average(){
        CabOperations cabOperations = new CabOperations("R");
        CabRide[] multipleRides = {(new CabRide(25,4))
                             ,(new CabRide(50.0,1.5))
                             ,(new CabRide(60.0,2.5))};
        double totalCabFareCost = cabOperations.calculateTotalRideCost(multipleRides);
        int numberOfRides = cabOperations.getNumberOfRides(multipleRides);
        double averageCostOfMultipleRides = cabOperations.calculateAverageCostForRides(multipleRides);

        Assertions.assertEquals(30150,totalCabFareCost);
        Assertions.assertEquals(3,numberOfRides);
        Assertions.assertEquals(10050,averageCostOfMultipleRides);
    }

    @Test
    public void givenUserId_shouldReturnParticularRidesAndTheirInvoice(){
        CabOperations firstUserToRide = new CabOperations("R");
        CabOperations secondUserToRide = new CabOperations("R");

        CabRide[] totalRidesOfFirstUser = {(new CabRide(25,2))
                ,(new CabRide(50.0,1.5))
                ,(new CabRide(60.0,2.5))};
        CabRide[] totalRidesOfSecondUser = {(new CabRide(22,4))
                ,(new CabRide(60.0,2.5))
                ,(new CabRide(30.0,0.5))};

        firstUserToRide.addUserRideDetails(firstUserToRide,totalRidesOfFirstUser);
        secondUserToRide.addUserRideDetails(secondUserToRide,totalRidesOfSecondUser);

        double[] firstUser = firstUserToRide.getRideDetails(firstUserToRide.getUserId());
        double[] secondUser = secondUserToRide.getRideDetails(secondUserToRide.getUserId());

        Assertions.assertArrayEquals(new double[]{7650.0, 22950.0, 3.0},firstUser);
        Assertions.assertArrayEquals(new double[]{8773.333333333334, 26320.0, 3.0},secondUser);

    }

    @Test
    public void givenCustomerType_ShouldReturnRegardingCostWithRespectTo_Customer(){
        CabOperations firstPremiumUserRideDetails = new CabOperations( 12345,"Premium");
        CabOperations firstRegularUserRideDetails = new CabOperations(34343,"Regular");
        CabRide[] totalRidesOfPremiumUser = {(new CabRide(25,2))
                ,(new CabRide(50.0,1.5))
                ,(new CabRide(60.0,2.5))};
        CabRide[] totalRidesOfRegularUser = {(new CabRide(22,4))
                ,(new CabRide(60.0,2.5))
                ,(new CabRide(30.0,0.5))};
        firstPremiumUserRideDetails.addUserRideDetails(firstPremiumUserRideDetails,totalRidesOfPremiumUser);
        firstRegularUserRideDetails.addUserRideDetails(firstRegularUserRideDetails,totalRidesOfRegularUser);

        double[] firstUser = firstPremiumUserRideDetails.getRideDetails(firstPremiumUserRideDetails.getUserId());
        double[] secondUser = firstRegularUserRideDetails.getRideDetails(firstRegularUserRideDetails.getUserId());

        Assertions.assertArrayEquals(new double[]{15075.0, 45225.0, 3.0},firstUser);
        Assertions.assertArrayEquals(new double[]{8773.333333333334, 26320.0, 3.0},secondUser);

    }

}