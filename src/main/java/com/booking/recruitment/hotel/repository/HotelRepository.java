package com.booking.recruitment.hotel.repository;

import com.booking.recruitment.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByDeleted(boolean status);

    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(c.latitude)) *" +
            " cos(radians(c.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(c.latitude))))";
    @Query(value = "SELECT c FROM Hotel c WHERE " + HAVERSINE_FORMULA + " < :distance ORDER BY "+ HAVERSINE_FORMULA + " AND deleted = 'false' DESC", nativeQuery=true)
    List<Hotel> findHotelWithInDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distanceWithInKM);
}
