package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.dto.GenericResponse;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Hotel getHotelById(@PathVariable("id") Long id) {
    return hotelService.getHotelById(id);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GenericResponse deleteHotelById(@PathVariable("id") Long id) {
    return hotelService.deleteHotelById(id);
  }

  @GetMapping(value = "/search/{cityId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotelsWithInDistanceByCityId(@PathVariable("cityId") Long cityId, @RequestParam(defaultValue = "distance") String sortBy) {
    return hotelService.searchHotelsByCityIdAndDistance(cityId);
  }

}
