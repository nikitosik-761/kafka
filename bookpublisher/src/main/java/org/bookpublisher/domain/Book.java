package org.bookpublisher.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Books")
public class Book {

    @Id
    @SequenceGenerator(
            name = "books_id_seq",
            sequenceName = "books_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "books_id_seq"
    )
    private Long id;

    private String isbn;

    private String title;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;
}
