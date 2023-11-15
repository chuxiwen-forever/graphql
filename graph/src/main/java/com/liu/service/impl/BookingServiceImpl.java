package com.liu.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liu.entity.Booking;
import com.liu.entity.Event;
import com.liu.repository.BookingRepository;
import com.liu.repository.EventRepository;
import com.liu.resolver.BookingResolver;
import com.liu.resolver.EventResolver;
import com.liu.service.BookingService;
import com.liu.vo.BookingVO;
import com.liu.vo.EventVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BookingResolver bookingResolver;

    @Autowired
    private EventResolver eventResolver;

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

    @Override
    public EventVO cancelBooking(String userId, String bookingId) {
        if (StrUtil.isBlankIfStr(userId) || StrUtil.isBlankIfStr(bookingId)) {
            throw new RuntimeException("bookingId为空");
        }
        int parseBookingId = Integer.parseInt(bookingId);
        Booking booking = bookingRepository.getBookingById(parseBookingId);
        if (ObjectUtil.isEmpty(booking)) {
            throw new RuntimeException(String.format("不存在bookingId为 %s 的booking", bookingId));
        }
        if (Integer.parseInt(userId) != booking.getUserId()) {
            throw new RuntimeException("你没有权限删除其他人的数据");
        }
        bookingRepository.removeBookingById(parseBookingId);
        Event eventById = eventRepository.getEventById(booking.getEventId());
        if (ObjectUtil.isEmpty(eventById)) {
            throw new RuntimeException("内部错误：event不对应");
        }
        return eventResolver.toEventVO(eventById);
    }

    @Override
    public List<BookingVO> getAllBookingByUserId(String userId) {
        int parseUserId = Integer.parseInt(userId);
        List<Booking> bookingList = bookingRepository.getBookingListByUserId(parseUserId);
        if (ArrayUtil.isEmpty(bookingList)) {
            log.info("userId为{}的用户bookingList列表为空", userId);
            return Collections.emptyList();
        }
        return bookingList.stream().map(bookingResolver::toBookingVO).collect(Collectors.toList());
    }
}
