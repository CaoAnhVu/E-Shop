package com.hutech.Shop.model;


import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BLOG")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_USERS", referencedColumnName = "ID_USERS")
    private User user;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "IMG")
    private String img;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "DATEBEGIN", nullable = false)
    private java.sql.Date dateBegin;

    @Column(name = "META")
    private String meta;

    @Column(name = "`ORDER`", nullable = false)
    private int order;

    @Column(name = "LINK")
    private String link;

    @Column(name = "HIDE", nullable = false)
    private boolean hide;
}
