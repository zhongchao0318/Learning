package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 导航 info
 */
@Entity
@Table(name = "menuinfo")
@Data
public class MenuInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6766726833522066159L;

    @Column(name = "node_name")
    private String name;        //节点名称

    @Column(name = "parent_node")
    private String parentNode;  //父节点

    @Column(name = "is_root")
    private boolean root;       //是否是根节点

    @Column(name = "site")
    private String site;        //位置

    @Column(name = "is_show")
    private boolean show;       //显示

    @Column(name = "status")
    private String status;      //状态

}
