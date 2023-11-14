package com.liu.service;

import com.liu.vo.BookingVO;

public interface BookingService {
    /**
     * 根据用户id和event id创建booking
     * @param userId 用户id
     * @param eventId eventId
     * @return 填充的bookingVo
     */
    BookingVO insertBooking(String userId, String eventId);
}
