package com.systex.dddlab;

import org.axonframework.config.Configurer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {
    @Autowired
    private Configurer configurer;

    @Test
    public void doTest0() {

    }

    @Test
    @Disabled("skip for negative demo")
    public void doTest1() {
        Assertions.fail();
    }

    @Test
    public void configurerNotNull() {
        Assertions.assertNotNull(configurer);
    }
}
