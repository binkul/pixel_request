package com.example.pixel_request.algorithm;

import com.example.pixel_request.position.Point;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class NearestNeighbor {

    public List<Point> Generator(List<Point> points, int start) {
        List<Point> result = new ArrayList<>();
        Point startPoint;
        Point nearestPoint;

        if (points == null) return result;
        if (points.size() <= 1) return points;
        if (start >= points.size()) return result;

        startPoint = points.get(start);
        startPoint.setVisited(true);
        result.add(startPoint);

        for (int i = 0; i < points.size() - 1; i++) {
            nearestPoint = findNearest(points, startPoint);
            nearestPoint.setVisited(true);
            result.add(nearestPoint);
            startPoint = nearestPoint;
        }

        return result;
    }

    private Point findNearest(List<Point> points, Point startPoint) {
        BigDecimal oldDistance = BigDecimal.ZERO;
        BigDecimal newDistance;
        Point nearestPoint = startPoint;
        boolean firstLoop = true;

        for (Point point : points) {
            if (point.isVisited()) continue;

            if (firstLoop) {
                oldDistance = getDistance(startPoint, point);
                nearestPoint = point;
                firstLoop = false;
            } else {
                newDistance = getDistance(startPoint, point);
                if (oldDistance.compareTo(newDistance) > 0) {
                    oldDistance = newDistance;
                    nearestPoint = point;
                }
            }
        }
        return nearestPoint;
    }

    private BigDecimal getDistance(Point start, Point end) {
        BigDecimal x_dif = end.getX().subtract(start.getX());
        BigDecimal y_dif = end.getY().subtract(start.getY());
        return x_dif.pow(2).add(y_dif.pow(2));
    }
}