package com.example.pixel_request.service;

import com.example.pixel_request.algorithm.Luhna;
import com.example.pixel_request.algorithm.NearestNeighbor;
import com.example.pixel_request.mapper.PointMapper;
import com.example.pixel_request.position.Point;
import com.example.pixel_request.position.PointSet;
import com.example.pixel_request.position.dto.PointSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PixelService {

    private final NearestNeighbor nearestNeighbor;
    private final PointMapper mapper;

    public String getControl(String digits) {
        int result = Luhna.generate(digits, false);
        return "Control sum of '" + digits + "' is: " + result;
    }

    public String checkControlSum(String digits) {
        String result = Luhna.generate(digits, true) == 0 ? "correct" : "incorrect";
        return "The number '" + digits + "' is " + result;
    }

    public PointSetDto solveNeighbor(PointSetDto pointSetDto, Integer start) {
        PointSet pointSet = mapper.mapToPointSet(pointSetDto);
        List<Point> points = nearestNeighbor.getDistanceFromPoint(pointSet.getPoints(), start);
        return mapper.mapToPointSetDto(points);
    }

    public PointSetDto solveShortNeighbor(PointSetDto pointSetDto) {
        PointSet pointSet = mapper.mapToPointSet(pointSetDto);
        List<Point> points = nearestNeighbor.getShortestDistance(pointSet.getPoints());
        return mapper.mapToPointSetDto(points);
    }
}
