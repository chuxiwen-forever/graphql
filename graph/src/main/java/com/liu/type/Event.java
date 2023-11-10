package com.liu.type;

import com.liu.entity.EventEntity;
import com.liu.util.DateUtil;
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

    public static Event fromEntity(EventEntity eventEntity) {
        Event event = new Event();
        event.setId(eventEntity.getId().toString());
        event.setDate(DateUtil.formatDateInISOString(eventEntity.getDate()));
        event.setDescription(eventEntity.getDescription());
        event.setTitle(eventEntity.getTitle());
        event.setPrice(eventEntity.getPrice());
        return event;
    }
}
