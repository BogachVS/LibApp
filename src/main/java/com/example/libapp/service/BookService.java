package com.example.libapp.service;

import com.example.libapp.DTO.BookDTO;
import com.example.libapp.DTO.BookUpdateDTO;
import com.example.libapp.mapper.BookMapper;
import com.example.libapp.repository.AuthorRepository;
import com.example.libapp.repository.BookRepository;
import com.example.libapp.model.AuthorModel;
import com.example.libapp.model.BookModel;
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