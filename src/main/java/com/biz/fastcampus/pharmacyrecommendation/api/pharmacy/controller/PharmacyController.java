package com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.controller;

import com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.cache.PharmacyRedisTemplateService;
import com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.dto.PharmacyDto;
import com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.service.PharmacyRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PharmacyController {

    private final PharmacyRepositoryService pharmacyRepositoryService;
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;

    // 데이터 초기 셋팅을 위한 임시 메소드
    @GetMapping("/redis/save")
    public String save() {

        List<PharmacyDto> pharmacyDtoList = pharmacyRepositoryService.findAll()
                .stream().map(pharmacy -> PharmacyDto.builder()
                        .id(pharmacy.getId())
                        .pharmacyName(pharmacy.getPharmacyName())
                        .pharmacyAddress(pharmacy.getPharmacyAddress())
                        .latitude(pharmacy.getLatitude())
                        .longitude(pharmacy.getLongitude())
                        .build())
                .toList();

        pharmacyDtoList.forEach(pharmacyRedisTemplateService::save);

        return "success";

    }

}
