package com.example.libapp.controllers;

import com.example.libapp.DTO.BookDTO;
import com.example.libapp.repository.models.BookModel;
import com.example.libapp.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController
{
    private final BookService bookService;
    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> AddBook(@RequestBody BookDTO book)
    {
        try
        {
            bookService.AddBook(book);
            return ResponseEntity.ok("Book successfully added");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> GetBooks()
    {
        try
        {
            List<BookModel> books = bookService.GetBooksList();
            return ResponseEntity.ok(books);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> GetBookInfo(@PathVariable Long id)
    {
        try
        {
            BookModel book = bookService.GetBook(id);
            return ResponseEntity.ok(book);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> UpdateBook(@PathVariable Long id, @RequestBody BookDTO book)
    {
        try
        {
            BookModel updatedBook = bookService.UpdateBookInfo(id, book);
            return ResponseEntity.ok(updatedBook);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteBook(@PathVariable Long id)
    {
        try
        {
            bookService.DeleteBook(id);
            return ResponseEntity.ok("Book successfully deleted");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}