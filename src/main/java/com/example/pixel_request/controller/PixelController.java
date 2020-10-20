package com.example.pixel_request.controller;

import com.example.pixel_request.position.dto.PointDto;
import com.example.pixel_request.position.dto.PointSetDto;
import com.example.pixel_request.service.PixelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pixel")
public class PixelController {

    private final PixelService pixelService;

    @GetMapping(value = "/control")
    public String getControl(@RequestParam("value") String digits) {
        return pixelService.getControl(digits);
    }

    @GetMapping(value = "/check")
    public String checkSum(@RequestParam("value") String digits) {
        return pixelService.checkControlSum(digits);
    }

    @PostMapping(value = "/knn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PointSetDto solveNeighbor(@RequestBody PointSetDto pointSetDto, @RequestParam("start") Integer start) {
        return pixelService.solveNeighbor(pointSetDto, start);
    }
}
