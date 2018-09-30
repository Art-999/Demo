package com.betconstruct.demo.model;

public interface Item {
    boolean isGroup();

    int getId();

    default int getGroupId() {
       return -1;
    }
}
