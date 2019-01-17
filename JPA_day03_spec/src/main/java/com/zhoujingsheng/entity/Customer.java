package com.zhoujingsheng.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cst_customer")
@Data  //免写getset方法
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自动增长
    @Column(name = "cust_id")
     private String custId;

    @Column(name = "cust_name")
     private String custName;

    @Column(name = "cust_source")
     private String custSource;

    @Column(name = "cust_industry")
     private String custIndustry;

    @Column(name = "cust_level")
     private String custLevel;

    @Column(name = "cust_address")
     private String custAddress;

    @Column(name = "cust_phone")
     private String custPhone;

}
