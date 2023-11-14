package com.liu.vo;

import lombok.Data;

@Data
public class BookingVO {
    private int id;
    private int eventId;
    private EventVO event;
    private int userId;
    private UserVO user;
    private String createdAt;
    private String updatedAt;
}
