package com.example.pixel_request.algorithm;

import com.example.pixel_request.exception.InvalidArgumentsException;
import com.example.pixel_request.position.Point;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validator {

    void Validate(List<Point> points, int start) {
        if (points == null) throw new InvalidArgumentsException("List of point is null.");
        if (points.size() == 0) throw new InvalidArgumentsException("List of point is empty.");
        if (start < 0 || start >= points.size()) throw new InvalidArgumentsException("Starting index is out of range of point list.");
    }
}
