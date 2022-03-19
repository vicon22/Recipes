//package com.example.java_spring;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class User {
//
//    @Id
//    private long id;
//
//    @OneToMany(mappedBy = "user")
//    private List<Tweet> tweets = new ArrayList<>();
//
//}
//
//@Entity
//class UserDetails {
//
//    private long user_id;
//
//    @Id
//    private long user_detail_id;
//
//    private String name;
//    private String location;
//    private String email;
//
//}
//
//@Entity
//class Tweet {
//
//    @Id
//    @Column(name = "tweet_id")
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}
