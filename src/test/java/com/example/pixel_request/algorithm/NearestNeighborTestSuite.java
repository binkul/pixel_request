package com.example.pixel_request.algorithm;

import com.example.pixel_request.position.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NearestNeighborTestSuite {

    @Autowired
    private NearestNeighbor nearestNeighbor;

    @Test
    public void testDistanceCalculationTwoPoints() {
        // Given
        Point pointA = new Point("A", new BigDecimal("2"), new BigDecimal("3"), false);
        Point pointB = new Point("B", new BigDecimal("4"), new BigDecimal("7"), false);
        List<Point> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);

        // When
        List<Point> result = nearestNeighbor.getDistanceFromPoint(points, 0);
        BigDecimal distance = nearestNeighbor.getDistance();

        // Then
        assertEquals(new BigDecimal("20"), distance);
        assertTrue(result.contains(pointA));
        assertTrue(result.contains(pointB));
    }

    @Test
    public void testDistanceCalculationFourPoints() {
        // Given
        Point pointA = new Point("A", new BigDecimal("12"), new BigDecimal("11"), false);
        Point pointB = new Point("B", new BigDecimal("7"), new BigDecimal("5"), false);
        Point pointC = new Point("C", new BigDecimal("13"), new BigDecimal("19"), false);
        Point pointD = new Point("D", new BigDecimal("3"), new BigDecimal("5"), false);
        List<Point> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);

        // When
        List<Point> result = nearestNeighbor.getDistanceFromPoint(points, 0);
        BigDecimal distance = nearestNeighbor.getDistance();

        // Then
        assertEquals(new BigDecimal("373"), distance);
        assertTrue(result.contains(pointA));
        assertTrue(result.contains(pointB));
        assertTrue(result.contains(pointC));
        assertTrue(result.contains(pointD));
    }

    @Test
    public void testDistanceAndSequenceFourPoints() {
        // Given
        Point pointA = new Point("A", new BigDecimal("12"), new BigDecimal("11"), false);
        Point pointB = new Point("B", new BigDecimal("7"), new BigDecimal("5"), false);
        Point pointC = new Point("C", new BigDecimal("13"), new BigDecimal("19"), false);
        Point pointD = new Point("D", new BigDecimal("3"), new BigDecimal("5"), false);
        List<Point> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);

        // When
        List<Point> result = nearestNeighbor.getDistanceFromPoint(points, 1);
        BigDecimal distance = nearestNeighbor.getDistance();

        // Then
        assertEquals(new BigDecimal("198"), distance);
        assertEquals(result.get(0).getName(), "B");
        assertEquals(result.get(1).getName(), "D");
        assertEquals(result.get(2).getName(), "A");
        assertEquals(result.get(3).getName(), "C");
    }

    @Test
    public void testDistanceAndSequenceFourPointsII() {
        // Given
        Point pointA = new Point("A", new BigDecimal("12"), new BigDecimal("11"), false);
        Point pointB = new Point("B", new BigDecimal("7"), new BigDecimal("5"), false);
        Point pointC = new Point("C", new BigDecimal("13"), new BigDecimal("19"), false);
        Point pointD = new Point("D", new BigDecimal("3"), new BigDecimal("5"), false);
        List<Point> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);

        // When
        List<Point> result = nearestNeighbor.getDistanceFromPoint(points, 3);
        BigDecimal distance = nearestNeighbor.getDistance();

        // Then
        assertEquals(new BigDecimal("142"), distance);
        assertEquals(result.get(0).getName(), "D");
        assertEquals(result.get(1).getName(), "B");
        assertEquals(result.get(2).getName(), "A");
        assertEquals(result.get(3).getName(), "C");
    }

    @Test
    public void testTwoDirectionDistanceAndSequenceFourPoints() {
        // Given
        Point pointA = new Point("A", new BigDecimal("12"), new BigDecimal("11"), false);
        Point pointB = new Point("B", new BigDecimal("7"), new BigDecimal("5"), false);
        Point pointC = new Point("C", new BigDecimal("13"), new BigDecimal("19"), false);
        Point pointD = new Point("D", new BigDecimal("3"), new BigDecimal("5"), false);
        List<Point> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);

        // When
        List<Point> result = nearestNeighbor.getDistanceFromPoint(points, 3);
        BigDecimal distance1 = nearestNeighbor.getDistance();
        pointA.setVisited(false);
        pointB.setVisited(false);
        pointC.setVisited(false);
        pointD.setVisited(false);
        List<Point> result2 = nearestNeighbor.getDistanceFromPoint(points, 2);
        BigDecimal distance2 = nearestNeighbor.getDistance();

        // Then
        assertEquals(distance1, distance2);
        assertEquals(result.get(0).getName(), "D");
        assertEquals(result.get(1).getName(), "B");
        assertEquals(result.get(2).getName(), "A");
        assertEquals(result.get(3).getName(), "C");
        assertEquals(result2.get(0).getName(), "C");
        assertEquals(result2.get(1).getName(), "A");
        assertEquals(result2.get(2).getName(), "B");
        assertEquals(result2.get(3).getName(), "D");
    }
}
