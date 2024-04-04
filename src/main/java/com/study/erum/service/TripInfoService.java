package com.study.erum.service;

import com.study.erum.model.TripInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.erum.mapper.TripInfoMapper; // TripInfoMapper 인터페이스 import

@Service
public class TripInfoService {

	@Autowired
    private TripInfoMapper tripInfoMapper; // TripInfoMapper 인터페이스 변수 선언


    // TripInfo 데이터 삽입 메서드
    public void insertTripInfo(TripInfo tripInfo) {
        tripInfoMapper.insertTripInfo(tripInfo); // TripInfoMapper의 메서드 호출
        System.out.println(tripInfo);
    }


    public TripInfo getTripInfoByPlaceName(String place_name) {
        return tripInfoMapper.getTripInfoByPlaceName(place_name);
    }
}
