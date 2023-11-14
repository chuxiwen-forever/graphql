package com.liu.repository.impl;

import com.liu.DO.BookingDO;
import com.liu.converter.BookingConverter;
import com.liu.entity.Booking;
import com.liu.mapper.BookingMapper;
import com.liu.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private BookingConverter bookingConverter;

    @Override
    public Booking createBooking(int userId, int eventId) {
        if (userId == 0 || eventId == 0) {
            throw new RuntimeException("eventId为空");
        }
        BookingDO bookingDO = new BookingDO();
        bookingDO.setCreatedAt(new Date());
        bookingDO.setUpdatedAt(new Date());
        bookingDO.setEventId(eventId);
        bookingDO.setUserId(userId);
        bookingMapper.insert(bookingDO);
        return bookingConverter.toBooking(bookingDO);
    }
}
