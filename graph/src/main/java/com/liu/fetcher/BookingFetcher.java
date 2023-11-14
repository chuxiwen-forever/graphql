package com.liu.fetcher;

import com.liu.custom.AuthContext;
import com.liu.service.BookingService;
import com.liu.vo.BookingVO;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
@Slf4j
public class BookingFetcher {

    @Autowired
    private BookingService bookingService;

    @DgsMutation
    public BookingVO bookEvent(@InputArgument(name = "eventId") String eventId, DgsDataFetchingEnvironment dfe) {
        AuthContext context = AuthContext.checkAuthAndReturnContext(dfe);
        return bookingService.insertBooking(context.getUser().getId(), eventId);
    }
}
