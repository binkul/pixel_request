package com.example.pixel_request.mapper;

import com.example.pixel_request.position.Point;
import com.example.pixel_request.position.PointSet;
import com.example.pixel_request.position.dto.PointDto;
import com.example.pixel_request.position.dto.PointSetDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PointMapper {

    private Point mapToPoint(PointDto pointDto) {
        return new Point(pointDto.getName(), new BigDecimal(pointDto.getX()), new BigDecimal(pointDto.getY()), false);
    }

    public PointSet mapToPointSet(PointSetDto pointSetDto) {
        if (pointSetDto != null) {
            List<Point> points = pointSetDto.getPointSet().stream()
                    .map(this::mapToPoint)
                    .collect(Collectors.toList());
            return new PointSet(points);
        } else {
            return new PointSet();
        }
    }

    public PointSetDto mapToPointSetDto(List<Point> points) {
        List<PointDto> pointDtos = points.stream()
                .map(i -> new PointDto(i.getName(), i.getX().toString(), i.getY().toString()))
                .collect(Collectors.toList());
        return new PointSetDto(pointDtos);
    }
}
