package org.bookpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        scanBasePackages = "org.bookpublisher"
)
@EnableScheduling
public class BookPublisherApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookPublisherApplication.class, args);
    }
}
