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
@Table(name = "Authors")
public class Author {

    @Id
    @SequenceGenerator(
            name = "authors_id_seq",
            sequenceName = "authors_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authors_id_seq"
    )
    private Long id;

    private String name;

    private Integer age;

}
