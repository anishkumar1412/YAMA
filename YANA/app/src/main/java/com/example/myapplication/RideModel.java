package com.example.myapplication;

public class RideModel {
    int img;
    String PickupAddress,RideDuration,RideTime,RideDate,Fair;
    RideModel(int img,String Fair,String PickupAddress,String RideDuration,String RideTime,String RideDate)
    {
        this.img = img;
        this.Fair = Fair;
        this.PickupAddress=PickupAddress;
        this.RideDuration = RideDuration;
        this.RideTime = RideTime;
        this.RideDate=RideDate;
    }

}
