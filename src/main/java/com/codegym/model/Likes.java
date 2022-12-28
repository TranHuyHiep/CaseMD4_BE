package com.codegym.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment cmt;

    public Likes(Post post, AppUser appUser) {
        this.post = post;
        this.appUser = appUser;
    }
}
