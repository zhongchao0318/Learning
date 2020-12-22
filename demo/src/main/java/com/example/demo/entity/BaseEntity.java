package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * table 公共字段
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -2688494701931540700L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "create_time")
    private Date createTime;//创建日期


    @Column(name = "modify_time")
    private Date modifyTime;//修改时间

}
