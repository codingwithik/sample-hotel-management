package com.booking.recruitment.hotel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericResponse {
    private String message;

    public GenericResponse(String message) {
        this.message = message;
    }

}
