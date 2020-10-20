package com.example.pixel_request.controller;

import com.example.pixel_request.position.dto.PointSetDto;
import com.example.pixel_request.service.PixelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/knn/short", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PointSetDto solveShortNeighbor(@RequestBody PointSetDto pointSetDto) {
        return pixelService.solveShortNeighbor(pointSetDto);
    }
}
