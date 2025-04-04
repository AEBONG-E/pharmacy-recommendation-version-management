package com.biz.fastcampus.pharmacyrecommendation.direction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OutputDto {
    private String pharmacyName;        // 약국명
    private String pharmacyAddress;     // 약국 주소
    private String directionUrl;        // 길안내 URL
    private String roadViewUrl;         // 로드뷰 URL
    private String distance;            // 고객 주소와 약국 주소간의 거리
}
