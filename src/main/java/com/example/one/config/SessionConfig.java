package com.example.one.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 360 * 24 * 60 * 60)
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

    private final String host;
    private final String password;

    SessionConfig(Environment environment) {
        host = environment.getProperty("REDIS_URL");
        password = environment.getProperty("REDIS_PASS");
    }

    /*
        The config command is disabled on Azure Cache.
        https://docs.microsoft.com/en-gb/azure/azure-cache-for-redis/cache-configure#redis-commands-not-supported-in-azure-cache-for-redis
     */
    @Bean
    public ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration defaultRedisConfig = new RedisStandaloneConfiguration();
        defaultRedisConfig.setHostName(host);
        defaultRedisConfig.setPassword(password);
        defaultRedisConfig.setPort(6380);

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().useSsl().build();

        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }
}