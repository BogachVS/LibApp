package com.example.libapp.serviceTest;

import com.example.libapp.DTO.BookDTO;
import com.example.libapp.DTO.BookUpdateDTO;
import com.example.libapp.model.AuthorModel;
import com.example.libapp.model.BookModel;
import com.example.libapp.repository.AuthorRepository;
import com.example.libapp.repository.BookRepository;
import com.example.libapp.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests
{
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    void whenAddBook_thenSaveSuccessfully()
    {
        BookDTO bookDTO = new BookDTO("Война и мир", 1L,1865,"Роман");
        AuthorModel author = new AuthorModel(1L, "Лев Толстой", 1828);
        BookModel savedBook = new BookModel(1L, "Война и мир", author,1865,"Роман");
        when(authorRepository.findById(bookDTO.getAuthorId())).thenReturn(Optional.of(author));
        when(bookRepository.save(any(BookModel.class))).thenReturn(savedBook);
        bookService.addBook(bookDTO);
        verify(bookRepository, times(1)).save(any(BookModel.class));
    }

    @Test
    void getBooksList_returnsList()
    {
        AuthorModel author = new AuthorModel(1L, "Лев Толстой", 1828);
        List<BookModel> books = List.of(
                new BookModel(1L, "Война и мир", author, 1865, "Роман"),
                new BookModel(2L, "Анна Каренина", author,1878,"Роман")
        );
        when(bookRepository.findAll()).thenReturn(books);
        List<BookDTO> result = bookService.getBooksList();
        assertEquals(2, result.size());
        assertEquals("Война и мир", result.get(0).getTitle());
        assertEquals("Анна Каренина", result.get(1).getTitle());
    }

    @Test
    void getBookById_returnsBook()
    {
        AuthorModel author = new AuthorModel(1L, "Лев Толстой", 1828);
        BookModel book = new BookModel(1L, "Война и мир", author,1865,"Роман");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookDTO result = bookService.getBook(1L);
        assertEquals("Война и мир", result.getTitle());
        assertEquals("Роман",result.getGenre());
        assertEquals(1865,result.getPublicationYear());
        assertEquals(author.getId(), result.getAuthorId());
    }

    @Test
    void getBookById_thenNotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.getBook(999L));
    }

    @Test
    void updateBookById_returnsUpdatedBook()
    {
        AuthorModel author = new AuthorModel(1L, "Лев Толстой", 1828);
        BookModel existingBook = new BookModel(1L, "Анна Каренина", author,1878,"Роман");
        BookUpdateDTO updateDTO = new BookUpdateDTO();
        updateDTO.setTitle("Война и мир");
        updateDTO.setPublicationYear(1865);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(BookModel.class))).thenReturn(existingBook);
        BookDTO result = bookService.updateBookInfo(1L, updateDTO);
        assertEquals("Война и мир", result.getTitle());
        assertEquals(1865,result.getPublicationYear());
        verify(bookRepository,times(1)).save(existingBook);
        assertEquals("Война и мир", existingBook.getTitle());
        assertEquals(1865,existingBook.getPublicationYear());
    }

    @Test
    void deleteBook_thenSuccess()
    {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
