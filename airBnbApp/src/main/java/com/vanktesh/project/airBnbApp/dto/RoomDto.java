package com.vanktesh.project.airBnbApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Data
@RequiredArgsConstructor
public class RoomDto {
    private Long id;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
    private Integer capacity;
}
