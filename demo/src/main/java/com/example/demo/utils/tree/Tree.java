package com.example.demo.utils.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree<T> {
    private String id;
    private String text;
    private String parentId;
    private List<Tree<T>> children = new ArrayList<Tree<T>>();
    private int status;
    private boolean hasParent = false;
    private boolean hasChild = false;
    private T t;
}
