package com.example.pixel_request.algorithm;

import com.example.pixel_request.position.Point;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class NearestNeighbor {

    private final Validator validator;
    private BigDecimal distance;

    public NearestNeighbor() {
        validator = new Validator();
        distance = BigDecimal.ZERO;
    }

    public List<Point> getShortestDistance(List<Point> points) {
        List<Point> result = new ArrayList<>();
        List<Point> tmpSet;
        BigDecimal tmpDistance = BigDecimal.ONE;
        boolean firstLoop = true;

        for (int i = 0; i < points.size(); i++) {
            clearVisit(points);
            tmpSet = getDistanceFromPoint(points, i);
            if (firstLoop) {
                tmpDistance = distance;
                result = tmpSet;
                firstLoop = false;
            } else {
                if (distance.compareTo(tmpDistance) <= 0) {
                    tmpDistance = distance;
                    result = tmpSet;
                }
            }
        }

        return result;
    }

    public List<Point> getDistanceFromPoint(List<Point> points, int start) {
        List<Point> result = new ArrayList<>();
        Point startPoint;
        Point nearestPoint;

        validator.Validate(points, start);
        if (points.size() == 1) return points;

        distance = BigDecimal.ZERO;
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
        BigDecimal shortDistance = BigDecimal.ZERO;
        BigDecimal newDistance;
        Point nearestPoint = startPoint;
        boolean firstLoop = true;

        for (Point point : points) {
            if (point.isVisited()) continue;

            newDistance = getDistance(startPoint, point);
            if (firstLoop) {
                shortDistance = newDistance;
                nearestPoint = point;
                firstLoop = false;
            } else {
                if (shortDistance.compareTo(newDistance) > 0) {
                    shortDistance = newDistance;
                    nearestPoint = point;
                }
            }
        }
        distance = distance.add(shortDistance);

        return nearestPoint;
    }

    private BigDecimal getDistance(Point start, Point end) {
        BigDecimal x_dif = end.getX().subtract(start.getX());
        BigDecimal y_dif = end.getY().subtract(start.getY());

        return x_dif.pow(2).add(y_dif.pow(2));
    }

    private void clearVisit(List<Point> points) {
        for (Point point : points) {
            point.setVisited(false);
        }
    }
}
