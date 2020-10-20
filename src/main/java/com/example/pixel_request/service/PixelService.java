package com.example.pixel_request.service;

import com.example.pixel_request.algorithm.Luhna;
import com.example.pixel_request.algorithm.NearestNeighbor;
import com.example.pixel_request.mapper.PointMapper;
import com.example.pixel_request.position.Point;
import com.example.pixel_request.position.PointSet;
import com.example.pixel_request.position.dto.PointDto;
import com.example.pixel_request.position.dto.PointSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PixelService {

    private final Luhna luhna;
    private final NearestNeighbor nearestNeighbor;
    private final PointMapper mapper;

    public String getControl(String digits) {
        int result = luhna.Generator(digits, false);
        return "Control sum of '" + digits + "' is: " + result;
    }

    public String checkControlSum(String digits) {
        String result = luhna.Generator(digits, true) == 0 ? "correct" : "incorrect";
        return "The number '" + digits + "' is " + result;
    }

    public PointSetDto solveNeighbor(PointSetDto pointSetDto, Integer start) {
        PointSet pointSet = mapper.mapToPointSet(pointSetDto);
        List<Point> points = nearestNeighbor.Generator(pointSet.getPoints(), start);
        return mapper.mapToPointSetDto(points);
    }
}
