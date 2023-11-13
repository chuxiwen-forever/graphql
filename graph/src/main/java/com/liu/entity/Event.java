package com.liu.entity;

import lombok.Data;

import java.util.Date;

/**
 * type Event {
 * id: ID!
 * title: String!
 * description: String!
 * price: Float!
 * date: String!
 * }
 */

@Data
public class Event {
    private String id;
    private String title;
    private String description;
    private Double price;
    private Date date;
    private int creatorId;
}
