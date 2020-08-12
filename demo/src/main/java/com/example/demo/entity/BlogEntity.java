package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * blog entity
 */
@Entity
@Table(name = "blog_table")
public class BlogEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6279033839384889187L;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "status_desc")
    private String statusDesc;
    @Column(name = "status")
    private String status;
}
