package com.rcoedo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.rcoedo.model")
public class RootContextConfig {}
