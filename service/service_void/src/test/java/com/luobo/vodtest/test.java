package com.luobo.vodtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    @Test
    public void test1(){
        String ids = "";
        List videoList = new ArrayList();
        videoList.add(1);
        videoList.add(2);
        for(Object id : videoList){
            ids = ids + id.toString() + ",";
        }
        ids = ids.substring(0, ids.lastIndexOf(","));
        System.out.println(ids);
    }
}
