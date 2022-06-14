package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.dto.GenericResponse;
import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;

  @Autowired
  public DefaultHotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAllByDeleted(false);
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAllByDeleted(false).stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  @Override
  public Hotel getHotelById(Long id) {

    Hotel hotel = hotelRepository.findById(id).orElseThrow(() ->
            new ElementNotFoundException("Hotel with id: "+id+" not found!"));

    if(hotel.isDeleted()) throw new BadRequestException("Hotel with id: "+id+" does not exists");

    return hotel;
  }

  @Override
  public GenericResponse deleteHotelById(Long id) {

    Hotel hotel = hotelRepository.findById(id).orElseThrow(() ->
            new ElementNotFoundException("Hotel with id: "+id+" not found!"));

    if(hotel.isDeleted()) throw new BadRequestException("Hotel with id: "+id+" does not exists");

    hotel.setDeleted(true);
    hotelRepository.save(hotel);

    return new GenericResponse("Hotel deleted successfully");
  }
}
