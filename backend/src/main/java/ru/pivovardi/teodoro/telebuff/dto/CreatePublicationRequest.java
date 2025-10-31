package ru.pivovardi.teodoro.telebuff.dto;

import lombok.Data;

@Data
public class CreatePublicationRequest {
    private String content;
    private String title;
//    private String author;
//    private List<String> hashtags;
}