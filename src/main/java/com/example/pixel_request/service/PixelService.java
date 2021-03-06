package com.example.pixel_request.service;

import com.example.pixel_request.algorithm.Luhna;
import com.example.pixel_request.algorithm.NearestNeighbor;
import com.example.pixel_request.exception.InvalidArgumentsException;
import com.example.pixel_request.mapper.PointMapper;
import com.example.pixel_request.position.Point;
import com.example.pixel_request.position.PointList;
import com.example.pixel_request.position.dto.PointListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PixelService {

    private final NearestNeighbor nearestNeighbor;
    private final PointMapper mapper;

    public String getControl(String digits) {
        String value = checkValue(digits);
        int result = Luhna.generate(value, false);

        return "Control sum of '" + digits + "' is: " + result;
    }

    public String checkControlSum(String digits) {
        String value = checkValue(digits);
        String result = Luhna.generate(value, true) == 0 ? "correct" : "incorrect";

        return "The number '" + digits + "' is " + result;
    }

    public PointListDto solveNeighbor(PointListDto pointListDto, Integer start) {
        PointList pointList = mapper.mapToPointList(pointListDto);
        List<Point> points = nearestNeighbor.getDistanceFromPoint(pointList.getPoints(), start);
        return mapper.mapToPointListDto(points, getDistance());
    }

    public PointListDto solveShortNeighbor(PointListDto pointListDto) {
        PointList pointList = mapper.mapToPointList(pointListDto);
        List<Point> points = nearestNeighbor.getShortestDistance(pointList.getPoints());
        return mapper.mapToPointListDto(points, getDistance());
    }

    private BigDecimal getDistance() {
        MathContext mc = new MathContext(6);
        return nearestNeighbor.getDistance().sqrt(mc);
    }

    private String checkValue(String value) {
        String result = cleanValue(value);
        if (result.length() == 0) throw new InvalidArgumentsException("Value is empty. The result is undetermined");
        return result;
    }

    private String cleanValue(String value) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char tmp = value.charAt(i);
            if (tmp >= '0' && tmp <= '9')
                result.append(tmp);
        }
        return result.toString();
    }
}
