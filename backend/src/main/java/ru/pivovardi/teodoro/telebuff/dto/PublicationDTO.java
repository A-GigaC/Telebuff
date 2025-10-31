package ru.pivovardi.teodoro.telebuff.dto;

import lombok.Data;

@Data
public class PublicationDTO {
    // String author; ? null
    // List<String> hashTags;
    // Timestamp updatedAt;
    // Timestamp createdAt;
    String id;
    String uri;
    String content;
    String title;
}
