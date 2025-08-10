package com.musinsa.assignment.shared.adapter.output.cache.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.musinsa.assignment.category.support.cache.CategoryCache
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@EnableCaching
@Configuration(proxyBeanMethods = false)
class CategoryRedisConfiguration {
    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
        val customObjectMapper =
            ObjectMapper()
                .registerModule(KotlinModule.Builder().build())
                .registerModule(JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .activateDefaultTyping(
                    BasicPolymorphicTypeValidator
                        .builder()
                        .allowIfBaseType(Any::class.java)
                        .allowIfBaseType(Collection::class.java)
                        .build(),
                    ObjectMapper.DefaultTyping.EVERYTHING,
                    JsonTypeInfo.As.PROPERTY,
                )
        return RedisCacheManagerBuilderCustomizer { builder ->
            CategoryCache.values().forEach {
                builder
                    .withCacheConfiguration(
                        it.cacheName,
                        RedisCacheConfiguration
                            .defaultCacheConfig()
                            .entryTtl(it.ttl)
                            .serializeKeysWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()),
                            ).serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(
                                    GenericJackson2JsonRedisSerializer(customObjectMapper),
                                ),
                            ).disableCachingNullValues(),
                    )
            }
        }
    }
}
