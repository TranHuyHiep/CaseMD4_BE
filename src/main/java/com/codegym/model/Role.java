package com.codegym.model;

import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.PUBLIC;

@Entity
@Table
@Data
@Builder
@RequiredArgsConstructor(access = PUBLIC)
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique=true)
    private String name;
}
