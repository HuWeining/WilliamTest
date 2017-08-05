//package com.example.demo.entity.TravelResource;
//
//import javax.persistence.*;
//
///**
// * Created by huweining on 2017/8/10.
// */
//
//public abstract class TravelResource {
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(name = "NAME")
//    private String name;
//    @Column(name = "GRADE")
//    private Integer grade;
//    @Column(name = "TYPE")
//    private Integer type;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getGrade() {
//        return grade;
//    }
//
//    public void setGrade(Integer grade) {
//        this.grade = grade;
//    }
//
//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
//}
