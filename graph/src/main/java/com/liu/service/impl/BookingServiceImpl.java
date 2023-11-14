package com.liu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liu.entity.Booking;
import com.liu.entity.Event;
import com.liu.repository.BookingRepository;
import com.liu.repository.EventRepository;
import com.liu.resolver.BookingResolver;
import com.liu.service.BookingService;
import com.liu.vo.BookingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BookingResolver bookingResolver;

    @Override
    public BookingVO insertBooking(String userId, String eventId) {
        if (StrUtil.isBlankIfStr(userId) || StrUtil.isBlankIfStr(eventId)) {
            throw new RuntimeException("eventId为空");
        }
        int parseEventId = Integer.parseInt(eventId);
        Event event = eventRepository.getEventById(parseEventId);
        if (ObjectUtil.isEmpty(event)) {
            throw new RuntimeException("event不存在");
        }
        Booking book = bookingRepository.createBooking(Integer.parseInt(userId), parseEventId);
        return bookingResolver.toBookingVO(book);
    }
}
