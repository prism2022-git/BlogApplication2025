package com.blog.app.Blog.application.entites;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    @Column(name = "post_title",nullable = false)
    private String title;
    @Column(name = "post_content",nullable = false)
    private String content;
    @Column(name = "post_imageName",nullable = false)
    private String imageName;
    @Column(name = "post_AddedDate",nullable = false)
    private Date postAddedDate;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade=CascadeType.ALL)
    private Set<Comment> comments=new HashSet<>();

}
