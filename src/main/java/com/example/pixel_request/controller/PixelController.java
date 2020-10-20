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

    @GetMapping(value = "/control/{digits}")
    public String getControl(@PathVariable String digits) {
        return pixelService.getControl(digits);
    }

    @GetMapping(value = "/check/{digits}")
    public String checkSum(@PathVariable String digits) {
        return pixelService.checkControlSum(digits);
    }

    @GetMapping(value = "/all")
    public PointSetDto getAll() {
        PointDto pointDto = new PointDto("A", "1", "0");
        PointDto pointDto1 = new PointDto("B", "2", "1");
        PointDto pointDto2 = new PointDto("c", "3", "2");
        List<PointDto> pointDtos = new ArrayList<>();
        pointDtos.add(pointDto);
        pointDtos.add(pointDto1);
        pointDtos.add(pointDto2);
        return new PointSetDto(pointDtos);
    }

    @PostMapping(value = "/knn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PointSetDto solveNeighbor(@RequestBody PointSetDto pointSetDto) {
        return pixelService.solveNeighbor(pointSetDto);
    }
}
