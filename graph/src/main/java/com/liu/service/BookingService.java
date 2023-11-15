package com.liu.service;

import com.liu.vo.BookingVO;
import com.liu.vo.EventVO;

import java.util.List;

public interface BookingService {
    /**
     * 根据用户id和event id创建booking
     *
     * @param userId  用户id
     * @param eventId eventId
     * @return 填充的bookingVo
     */
    BookingVO insertBooking(String userId, String eventId);

    /**
     * 根据bookingId取消book
     *
     * @param userId    userId
     * @param bookingId bookingId
     * @return 返回事件详情
     */
    EventVO cancelBooking(String userId, String bookingId);

    /**
     * 根据userId查询bookingVo列表
     *
     * @param userId userId
     * @return booking列表
     */
    List<BookingVO> getAllBookingByUserId(String userId);
}
