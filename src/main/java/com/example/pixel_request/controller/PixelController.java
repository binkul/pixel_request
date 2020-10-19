package com.example.pixel_request.controller;

import com.example.pixel_request.service.PixelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
