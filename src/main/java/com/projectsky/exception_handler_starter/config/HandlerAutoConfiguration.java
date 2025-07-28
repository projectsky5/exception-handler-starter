package com.projectsky.exception_handler_starter.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ConditionalOnProperty(prefix = "handler", name = "enabled", havingValue = "true")
public class HandlerAutoConfiguration {
}
