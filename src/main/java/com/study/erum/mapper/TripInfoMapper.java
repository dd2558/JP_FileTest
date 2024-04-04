package com.study.erum.mapper;

import com.study.erum.model.TripInfo;

public interface TripInfoMapper {
    // TripInfo 데이터 삽입 메서드
    void insertTripInfo(TripInfo tripInfo);
    
    TripInfo getTripInfoByPlaceName(String place_name);

    TripInfo getTripInfoByHashTag(String hashtag);
}
