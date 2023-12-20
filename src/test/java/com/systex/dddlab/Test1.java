package com.systex.dddlab;

import org.axonframework.commandhandling.CommandBus;
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
    @Autowired
    private CommandBus commandBus;

    @Test
    public void configurerNotNull() {
        Assertions.assertNotNull(configurer);
    }

    @Test
    public void commandBusNotNull() {
        CommandBus commandBus = configurer.start().commandBus();
        Assertions.assertNotNull(commandBus);
    }

    @Test
    public void wiredCommandBusNotNull() {
        Assertions.assertNotNull(commandBus);
    }

    @Test
    public void commandBusesShouldBeTheSame() {
        CommandBus cb = configurer.start().commandBus();
        Assertions.assertSame(cb, commandBus);
    }

}
