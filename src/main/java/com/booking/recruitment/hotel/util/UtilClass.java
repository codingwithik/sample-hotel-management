package com.booking.recruitment.hotel.util;

public class UtilClass {

    public static double distance(double lat1, double lon1) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat1);
        double lonDistance = Math.toRadians(lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
