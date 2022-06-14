package com.booking.recruitment.hotel.config;

import com.booking.recruitment.hotel.repository.CityRepository;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.CityService;
import com.booking.recruitment.hotel.service.HotelService;
import com.booking.recruitment.hotel.service.RatingService;
import com.booking.recruitment.hotel.service.impl.DefaultCityService;
import com.booking.recruitment.hotel.service.impl.DefaultHotelService;
import com.booking.recruitment.hotel.service.impl.DefaultRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Bean
    public HotelService hotelService() {
        return new DefaultHotelService(hotelRepository);
    }

    @Bean
    public CityService cityService() {
        return new DefaultCityService(cityRepository);
    }

    @Bean
    public RatingService ratingService() {
        return new DefaultRatingService(hotelService());
    }
}
