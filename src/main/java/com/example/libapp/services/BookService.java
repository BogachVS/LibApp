package com.example.libapp.services;

import com.example.libapp.DTO.BookDTO;
import com.example.libapp.repository.AuthorRepository;
import com.example.libapp.repository.BookRepository;
import com.example.libapp.repository.models.AuthorModel;
import com.example.libapp.repository.models.BookModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void AddBook(BookDTO body)
    {
        AuthorModel author = authorRepository.findById(body.getAuthor_id()).orElseThrow(()->new EntityNotFoundException("Author not found"));
        BookModel book = new BookModel();

        if(body.getTitle()!=null && !body.getTitle().isEmpty())
            book.setTitle(body.getTitle());
        else
            throw new IllegalArgumentException("Title cannot be empty");

        book.setAuthor(author);

        if(body.getYear() > author.getBirth_year())
            book.setPublication_year(body.getYear());
        else
            throw new IllegalArgumentException("Year of publishing is not valid");

        if(body.getGenre()!=null && !body.getGenre().isEmpty())
            book.setGenre(body.getGenre());
        else throw new IllegalArgumentException("Genre cannot be empty");

        bookRepository.save(book);

    }

    public List<BookModel> GetBooksList()
    {
        return bookRepository.findAll();
    }

    public BookModel GetBook(Long id)
    {
        return bookRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Book not found"));
    }

    public BookModel UpdateBookInfo(Long id, BookDTO body)
    {
        BookModel book = bookRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Book not found"));

        if(body.getTitle()!=null && !body.getTitle().isEmpty())
            book.setTitle(body.getTitle());
        else
            throw new IllegalArgumentException("Title cannot be empty");

        if(body.getAuthor_id() != 0)
        {
            AuthorModel author = authorRepository.findById(body.getAuthor_id()).orElseThrow(()->new EntityNotFoundException("Author not found"));
            book.setAuthor(author);
        }
        else
            throw new IllegalArgumentException("Author_id cannot be empty");

        if(body.getYear() > 0)
            book.setPublication_year(body.getYear());
        else
            throw new IllegalArgumentException("Year of publishing is not valid");

        if(body.getGenre()!=null && !body.getGenre().isEmpty())
            book.setGenre(body.getGenre());
        else throw new IllegalArgumentException("Genre cannot be empty");

        return bookRepository.save(book);
    }

    public void DeleteBook(Long id)
    {
        bookRepository.deleteById(id);
    }
}