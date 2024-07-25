package org.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration // 설정이라는 뜻
@EntityScan(basePackages = "org.delivery.db") // db 안에 Entity 어노테이션이 붙은 것들을 모두 스캔
@EnableJpaRepositories(basePackages = "org.delivery.db")
public class JpaConfig {
}
