package com.example.pixel_request.service;

import com.example.pixel_request.algorithm.Luhna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PixelService {

    private final Luhna luhna;

    public String getControl(String digits) {
        int result = luhna.Generator(digits, false);
        return "Control sum of '" + digits + "' is: " + result;
    }

    public String checkControlSum(String digits) {
        String result = luhna.Generator(digits, true) == 0 ? "correct" : "incorrect";
        return "The number '" + digits + "' is " + result;
    }
}
