package com.example.pixel_request.position.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PointSetDto {
    private List<PointDto> pointSet;
}
