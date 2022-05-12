package com.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

public class TestResourceFile {

    @Test
    void testFile(){
        InputStream inputStream = this.getClass().getResourceAsStream("/bpmn");
        File f1 = new File(String.valueOf(inputStream));
        String[] lists = f1.list();
        for (String list : lists) {
            System.out.println(list);
        }
        System.out.println(inputStream);

    }
}
