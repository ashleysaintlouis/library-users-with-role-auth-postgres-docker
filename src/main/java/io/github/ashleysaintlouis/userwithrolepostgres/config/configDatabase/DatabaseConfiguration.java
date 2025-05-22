package io.github.ashleysaintlouis.userwithrolepostgres.config.configDatabase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

/*    @Bean // Não recomendado para produção, é muito basico
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }*/

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // Pode aumentar a quantidade, maximo de conexão liberada
        config.setMinimumIdle(1); // Tamanho inicial do pool
        config.setPoolName("library_pool_hikari");
        config.setMaxLifetime(600000); //600 mil ms (10 minutos de vida da conexão)
        config.setConnectionTimeout(100000); // tempo para conseguir uma conexão
        config.setConnectionTestQuery("SELECT 1"); // teste para verificar a conexão com o banco

        return new HikariDataSource(config);
    }
}
