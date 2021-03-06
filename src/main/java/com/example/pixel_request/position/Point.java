package com.example.pixel_request.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Point {
    private String name;
    private BigDecimal x;
    private BigDecimal y;
    private boolean visited;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return visited == point.visited &&
                Objects.equals(name, point.name) &&
                Objects.equals(x, point.x) &&
                Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y, visited);
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", visited=" + visited +
                '}';
    }
}
