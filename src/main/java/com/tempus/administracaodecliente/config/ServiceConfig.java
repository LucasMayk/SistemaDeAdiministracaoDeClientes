package com.tempus.administracaodecliente.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tempus.administracaodecliente.service.CadastroClienteService;

@Configuration
@ComponentScan(basePackageClasses = CadastroClienteService.class)
public class ServiceConfig {
	
}
