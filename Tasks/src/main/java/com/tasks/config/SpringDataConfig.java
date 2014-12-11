package com.tasks.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = { "com.tasks.repositories",
		"com.tasks.security" })
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.tasks" })
@EntityScan(basePackages = { "com.tasks.entities", "com.tasks.security" })
@EnableTransactionManagement
public class SpringDataConfig {

	@Autowired
	private Environment env;

	// la source de données MySQL
	@Bean
	public DataSource dataSource() {
		try {			
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(env
					.getRequiredProperty("app.jdbc.driverClassName"));
			dataSource.setUrl(env.getRequiredProperty("app.jdbc.url"));
			dataSource
					.setUsername(env.getRequiredProperty("app.jdbc.username"));
			dataSource
					.setPassword(env.getRequiredProperty("app.jdbc.password"));
			return dataSource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// le provider JPA - n'est pas nécessaire si on est satisfait des valeurs
	// par
	// défaut utilisées par Spring boot
	// ici on le définit pour activer / désactiver les logs SQL
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	// l'EntityManageFactory et le TransactionManager sont définis avec des
	// valeurs par défaut par Spring boot

}
