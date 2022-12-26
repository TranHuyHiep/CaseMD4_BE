package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    private AppUser appUser;
    @Column(columnDefinition = "LongText")
    private String content;

    @Column(columnDefinition = "DateTime")
    private LocalDateTime time;

    @Column(columnDefinition = "Bigint default 0")
    private int likeCount;

    @Column(columnDefinition = "Bigint default 0")
    private int cmtCount;

    @Column(columnDefinition = "int")
    private int status;
}
