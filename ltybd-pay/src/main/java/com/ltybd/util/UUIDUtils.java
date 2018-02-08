package com.ltybd.util;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
