package com.liu.vo;

import lombok.Data;

@Data
public class EventVO {
    private String id;
    private String title;
    private String description;
    // 注意：GraphQL.Float => Java.Double
    private Double price;
    private String date;
    private UserVO creator;
}
