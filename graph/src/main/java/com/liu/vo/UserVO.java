package com.liu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserVO {
    private String id;
    private String email;
    private String password;
    private List<EventVO> createdEvents = new ArrayList<>();
    private List<BookingVO> bookings = new ArrayList<>();
}
