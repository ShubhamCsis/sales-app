package com.example.salesapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SalesAppApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // This test will simply start the application context and verify that there are no issues.
    }

    @Test
    void testSalesServiceBeanExists() {
        // Verify that the SalesService bean is loaded in the application context
        assertNotNull(context.getBean("salesService"));
    }
}
