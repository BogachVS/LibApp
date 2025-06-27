package com.example.libapp.services;

import com.example.libapp.DTOs.BookDTO;
import com.example.libapp.DTOs.BookUpdateDTO;
import com.example.libapp.mappers.BookMapper;
import com.example.libapp.repositories.AuthorRepository;
import com.example.libapp.repositories.BookRepository;
import com.example.libapp.models.AuthorModel;
import com.example.libapp.models.BookModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService
{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void addBook(BookDTO body)
    {
        AuthorModel author = authorRepository.findById(body.getAuthorId()).orElseThrow(()->new EntityNotFoundException("Author not found"));
        BookModel book = BookMapper.INSTANCE.bookDTOToBookModel(body);
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public List<BookDTO> getBooksList()
    {
        return BookMapper.INSTANCE.booksToBookDTOs(bookRepository.findAll());
    }

    public BookDTO getBook(Long id)
    {
        BookModel book = bookRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Book not found"));
        return BookMapper.INSTANCE.bookToBookDTO(book);
    }

    public BookDTO updateBookInfo(Long id, BookUpdateDTO body)
    {
        BookModel book = bookRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Book not found"));
        if (body.getAuthorId() != null)
        {
            AuthorModel author = authorRepository.findById(body.getAuthorId()).orElseThrow(() -> new EntityNotFoundException("Author not found"));
            book.setAuthor(author);
        }
        BookMapper.INSTANCE.updateBookFromBookDTO(body, book);
        bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDTO(book);
    }

    public void deleteBook(Long id)
    {
        bookRepository.deleteById(id);
    }
}