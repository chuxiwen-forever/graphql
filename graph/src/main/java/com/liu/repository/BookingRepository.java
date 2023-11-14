package com.liu.repository;

import com.liu.entity.Booking;

public interface BookingRepository {
    /**
     * 根据用户id 和 evenId创建booking
     * @param userId 用户id
     * @param eventId eventId
     * @return booking
     */
    Booking createBooking(int userId, int eventId);
}
