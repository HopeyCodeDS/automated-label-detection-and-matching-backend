package be.kdg.prog6.MatcherContext.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "be.kdg.prog6.MatcherContext.adapters.out")
public class JpaConfig {
}