package com.musinsa.assignment.category.adapter.output.persistence.jpa

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MySQLContainer

@TestConfiguration(proxyBeanMethods = false)
class TestcontainerConfiguration {
    @Bean
    @ServiceConnection
    fun mysqlContainer(): MySQLContainer<*> =
        MySQLContainer("mysql:8.0")
            .withUsername("admin")
            .withPassword("1234")
            .withDatabaseName("test")
            .withReuse(true)
}
