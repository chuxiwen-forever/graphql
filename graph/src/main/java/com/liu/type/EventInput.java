package com.liu.type;

import lombok.Data;

/**
 * input EventInput {
 * title: String!
 * description: String!
 * price: Float!
 * date: String!
 * }
 */

@Data
public class EventInput {
    private String title;
    private String description;
    // 注意：GraphQL.Float => Java.Double
    private Double price;
    private String date;
}
