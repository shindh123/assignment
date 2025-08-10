package com.musinsa.assignment.shared.adapter.output.persistence.jpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = ["com.musinsa.assignment.*.adapter.output.persistence.jpa.entity"])
@EnableJpaRepositories(basePackages = ["com.musinsa.assignment.*.adapter.output.persistence.jpa.repository"])
class JpaConfiguration
