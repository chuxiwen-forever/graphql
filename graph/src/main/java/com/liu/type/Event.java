package com.liu.type;

import lombok.Data;

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
    private String date;
}
