package org.bookpersistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "org.bookpersistence"
)
public class BookPersistenceApplication
{
    public static void main( String[] args ) {
        SpringApplication.run(BookPersistenceApplication.class, args);
    }
}
