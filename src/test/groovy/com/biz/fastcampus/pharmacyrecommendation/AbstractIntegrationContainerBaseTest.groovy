package com.biz.fastcampus.pharmacyrecommendation;

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification;

@ContextConfiguration(classes = FastcampusPharmacyRecommendationApplication.class)
@SpringBootTest
abstract class AbstractIntegrationContainerBaseTest extends Specification {

    static final GenericContainer MY_REDIS_CONTAINER;

    static {
        MY_REDIS_CONTAINER = new GenericContainer<>("redis:6")
                .withExposedPorts(6379)

        MY_REDIS_CONTAINER.start()

        System.setProperty("spring.redis.host", MY_REDIS_CONTAINER.getHost());
        System.setProperty("spring.redis.port", MY_REDIS_CONTAINER.getMappedPort(6379).toString());
    }

}
