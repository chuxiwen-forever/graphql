package com.liu.fetcher;

import com.liu.custom.AuthContext;
import com.liu.service.BookingService;
import com.liu.service.EventService;
import com.liu.service.UserService;
import com.liu.vo.BookingVO;
import com.liu.vo.EventVO;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.*;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
@Slf4j
public class BookingFetcher {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @DgsQuery
    public List<BookingVO> bookings(DgsDataFetchingEnvironment dfe) {
        AuthContext context = AuthContext.checkAuthAndReturnContext(dfe);
        return bookingService.getAllBookingByUserId(context.getUser().getId());
    }

    @DgsMutation
    public EventVO cancelBooking(@InputArgument(name = "bookingId") String bookingId, DgsDataFetchingEnvironment dfe) {
        AuthContext context = AuthContext.checkAuthAndReturnContext(dfe);
        return bookingService.cancelBooking(context.getUser().getId(), bookingId);
    }

    @DgsMutation
    public BookingVO bookEvent(@InputArgument(name = "eventId") String eventId, DgsDataFetchingEnvironment dfe) {
        AuthContext context = AuthContext.checkAuthAndReturnContext(dfe);
        return bookingService.insertBooking(context.getUser().getId(), eventId);
    }

    @DgsData(parentType = "Booking")
    public EventVO event(DgsDataFetchingEnvironment dfe) {
        BookingVO bookingVO = dfe.getSource();
        int eventId = bookingVO.getEventId();
        return eventService.getEventById(eventId);
    }

    @DgsData(parentType = "Booking")
    public CompletableFuture<UserVO> user(DgsDataFetchingEnvironment dfe) {
        BookingVO bookingVO = dfe.getSource();
        DataLoader<Integer, UserVO> userVo = dfe.getDataLoader("userVo");
        int userId = bookingVO.getUserId();
        return userVo.load(userId);
    }
}
