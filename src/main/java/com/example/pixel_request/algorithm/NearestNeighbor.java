package com.example.pixel_request.algorithm;

import com.example.pixel_request.position.Point;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class NearestNeighbor {

    private final Validator validator;
    private BigDecimal shortDistance;

    public NearestNeighbor() {
        validator = new Validator();
        shortDistance = BigDecimal.ZERO;
    }

    public List<Point> getShortestDistance(List<Point> points) {
        List<Point> result = new ArrayList<>();
        List<Point> tmpSet;
        BigDecimal tmpDistance = BigDecimal.ONE;

        for (int i = 0; i < points.size(); i++) {
            shortDistance = BigDecimal.ZERO;
            clearVisit(points);
            tmpSet = getDistanceFromPoint(points, i);
            if (shortDistance.compareTo(tmpDistance) <= 0) {
                tmpDistance = shortDistance;
                result = tmpSet;
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

            newDistance = getDistance(startPoint, point);
            if (firstLoop) {
                oldDistance = newDistance;
                nearestPoint = point;
                firstLoop = false;
            } else {
                if (oldDistance.compareTo(newDistance) > 0) {
                    oldDistance = newDistance;
                    nearestPoint = point;
                    shortDistance = shortDistance.add(newDistance);
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

    private void clearVisit(List<Point> points) {
        for (Point point : points) {
            point.setVisited(false);
        }
    }
}
