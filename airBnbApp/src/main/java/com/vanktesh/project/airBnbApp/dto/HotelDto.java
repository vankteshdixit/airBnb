package com.vanktesh.project.airBnbApp.dto;

import com.vanktesh.project.airBnbApp.entity.HotelContactInfo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}