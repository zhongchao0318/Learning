package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * table 公共字段
 */
@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "creator")
    private String creator; //创建人

    @Column(name = "create_time")
    private Date createTime;//创建日期

    @Column(name = "modify_by")
    private String modifyBy;//修改人

    @Column(name = "modify_time")
    private Date modifyTime;//修改时间

}
