package com.ceshiren.hogwarts.frameworkwechat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class JavaTest {
    @Test
    void reflect() throws ClassNotFoundException {
        Class<?> str = Class.forName("java.lang.String");
        Arrays.stream(str.getMethods()).forEach(m->System.out.println(m));

    }
}
