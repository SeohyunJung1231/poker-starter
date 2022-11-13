package com.jeong.pokerstarter.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class MysqlConfig (
) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun mysqlDataSource(): DataSource {
        return DataSourceBuilder.create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun mysqlEntityManagerFactory(
        builder: EntityManagerFactoryBuilder, mysqlDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(mysqlDataSource)
            .packages(
                // entity 위치
                "tv.anypoint.pi.meta.domain"
            )
            .properties(
                mutableMapOf(
                    Pair("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"),
                    Pair(
                        "hibernate.physical_naming_strategy",
                        "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy"
                    )
                )
            )
            .persistenceUnit("mysql")
            .build()
    }

    @Bean
    fun mysqlPiTransactionManager(
        mysqlEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(mysqlEntityManagerFactory)
    }
}