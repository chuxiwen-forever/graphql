package com.liu.resolver;

import com.liu.entity.Booking;
import com.liu.vo.BookingVO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface BookingResolver {

    BookingVO toBookingVO(Booking booking);
}
