package com.liu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Booking {

    private int id;

    private int userId;

    private int eventId;

    private Date createdAt;

    private Date updatedAt;
}
