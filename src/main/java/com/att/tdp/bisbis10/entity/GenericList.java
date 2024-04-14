package com.att.tdp.bisbis10.entity;

import java.util.LinkedList;
import java.util.List;

public class GenericList<T> {

    private List<T> list;


    public GenericList(){
        this.list = new LinkedList<T>();
    }



}
