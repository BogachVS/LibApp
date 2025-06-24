package com.example.libapp.repository.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;
    private int publication_year;
    private String genre;
}