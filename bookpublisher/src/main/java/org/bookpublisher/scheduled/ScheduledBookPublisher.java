package org.bookpublisher.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.bookpublisher.repositories.BookRepository;
import org.bookpublisher.services.BookPublisherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledBookPublisher {

    private long counter;

    private final BookRepository bookRepository;

    private final BookPublisherService bookPublisherService;

    public ScheduledBookPublisher(final BookPublisherService bookPublisherService, final BookRepository bookRepository) {
        resetCounter();
        this.bookPublisherService = bookPublisherService;
        this.bookRepository = bookRepository;
    }

    @Scheduled(cron = "0/30 * * * * *")
    public void publishBook() {
        bookRepository.findById(counter).ifPresentOrElse(book -> {
            counter += 1;
            bookPublisherService.publish(book);
            log.info("Book '{}' [{}] published.", book.getTitle(), book.getIsbn());
        }, () -> resetCounter());
    }

    private void resetCounter() {
        this.counter = 1;
    }


}
