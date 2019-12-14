package com.example.demo.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories({ "com.example.demo.repository", "com.example.demo.infraestructura.repository.database" })
public class PersistenceConfig {

}
