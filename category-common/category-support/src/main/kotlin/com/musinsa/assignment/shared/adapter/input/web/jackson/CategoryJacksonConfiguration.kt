package com.musinsa.assignment.shared.adapter.input.web.jackson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration(proxyBeanMethods = false)
class CategoryJacksonConfiguration {
    @Bean
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer =
        Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder.modules {
                KotlinModule.Builder().build()
                JavaTimeModule()
            }
            builder.featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            )
            builder.featuresToEnable(
                SerializationFeature.INDENT_OUTPUT,
                DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
            )
            builder.serializationInclusion(JsonInclude.Include.NON_NULL)
            builder.postConfigurer {
                it.deactivateDefaultTyping()
            }
        }
}
