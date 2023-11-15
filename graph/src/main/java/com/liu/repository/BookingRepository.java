package com.liu.repository;

import com.liu.entity.Booking;

import java.util.List;

public interface BookingRepository {
    /**
     * 根据用户id 和 evenId创建booking
     *
     * @param userId  用户id
     * @param eventId eventId
     * @return booking
     */
    Booking createBooking(int userId, int eventId);

    /**
     * 根据id查询booking
     *
     * @param id 主键id
     * @return booking详细数据
     */
    Booking getBookingById(int id);

    /**
     * 通过id删除booking
     *
     * @param id 主键id
     */
    void removeBookingById(int id);

    /**
     * 根据用户id查询booking列表
     *
     * @param userId userId
     * @return booking列表
     */
    List<Booking> getBookingListByUserId(int userId);
}
