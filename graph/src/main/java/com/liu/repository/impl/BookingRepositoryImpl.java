package com.liu.repository.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.DO.BookingDO;
import com.liu.converter.BookingConverter;
import com.liu.entity.Booking;
import com.liu.mapper.BookingMapper;
import com.liu.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
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

    @Override
    public Booking getBookingById(int id) {
        if (id == 0) {
            throw new RuntimeException("bookingId为空");
        }
        LambdaQueryWrapper<BookingDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BookingDO::getId, id);
        BookingDO bookingDO = bookingMapper.selectOne(wrapper);
        if (ObjectUtil.isEmpty(bookingDO)) {
            log.info("不存在id为{}的booking", id);
            return null;
        }
        return bookingConverter.toBooking(bookingDO);
    }

    @Override
    public void removeBookingById(int id) {
        if (id == 0) {
            throw new RuntimeException("bookingId为空");
        }
        boolean success = bookingMapper.deleteById(id) == 1;
        if (!success) {
            log.info("删除失败...");
        }
    }

    @Override
    public List<Booking> getBookingListByUserId(int userId) {
        if (userId == 0) {
            throw new RuntimeException("userId 为空");
        }
        LambdaQueryWrapper<BookingDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BookingDO::getUserId, userId);
        List<BookingDO> bookingDOS = bookingMapper.selectList(wrapper);
        if (ArrayUtil.isEmpty(bookingDOS)) {
            log.info("BookingRepository.getBookingListByUserId ==> bookingDOS为空");
            return Collections.emptyList();
        }
        return bookingDOS.stream().map(bookingConverter::toBooking).collect(Collectors.toList());
    }
}
