package com.fuzamei.utils;

import java.util.UUID;

/**
 * Created by ylx on 2018/12/20.
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
