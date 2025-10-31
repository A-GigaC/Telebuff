package ru.pivovardi.teodoro.telebuff.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "publications")
@Data
public class Publication {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "uri", unique = true)
    private String uri;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "title")
    private String title;



//    @Column(name = "author")
//    private String author;
//
//    @ElementCollection
//    @CollectionTable(name = "story_hashtags", joinColumns = @JoinColumn(name = "story_id"))
//    @Column(name = "hashtag")
//    private List<String> hashTags = new ArrayList<>();
//
//    // Дополнительные поля, если нужны
//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}