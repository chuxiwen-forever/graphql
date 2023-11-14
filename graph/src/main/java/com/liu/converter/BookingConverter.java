package com.liu.converter;

import com.liu.DO.BookingDO;
import com.liu.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface BookingConverter {

    Booking toBooking(BookingDO bookingDO);
}
