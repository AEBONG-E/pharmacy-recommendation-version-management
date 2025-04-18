package com.biz.fastcampus.pharmacyrecommendation.direction.controller;

import com.biz.fastcampus.pharmacyrecommendation.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DirectionController {

    private final DirectionService directionService;

    @GetMapping("/dir/{encodedId}")
    public String searchDirection(@PathVariable("encodedId") String encodedId) {
        String result = this.directionService.findDirectionUrlById(encodedId);
        log.info("[DirectionController searchDirection] direction url: {}", result);
        return "redirect:" + result;
    }

}
