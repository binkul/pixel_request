package com.example.pixel_request.mapper;

import com.example.pixel_request.position.Point;
import com.example.pixel_request.position.PointList;
import com.example.pixel_request.position.dto.PointDto;
import com.example.pixel_request.position.dto.PointListDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PointMapper {

    private Point mapToPoint(PointDto pointDto) {
        return new Point(pointDto.getName(), new BigDecimal(pointDto.getX()), new BigDecimal(pointDto.getY()), false);
    }

    public PointList mapToPointList(PointListDto pointListDto) {
        if (pointListDto != null) {
            List<Point> points = pointListDto.getPointSet().stream()
                    .map(this::mapToPoint)
                    .collect(Collectors.toList());
            return new PointList(points);
        } else {
            return new PointList();
        }
    }

    public PointListDto mapToPointListDto(List<Point> points, BigDecimal distance) {
        List<PointDto> pointDtos = points.stream()
                .map(i -> new PointDto(i.getName(), i.getX().toString(), i.getY().toString()))
                .collect(Collectors.toList());

        return new PointListDto(distance.toString(), pointDtos);
    }
}
