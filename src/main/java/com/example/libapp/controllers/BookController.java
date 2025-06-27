package com.example.libapp.controllers;

import com.example.libapp.DTOs.BookDTO;
import com.example.libapp.DTOs.BookUpdateDTO;
import com.example.libapp.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController
{
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO book)
    {
        try
        {
            bookService.addBook(book);
            return ResponseEntity.ok("Book successfully added");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks()
    {
        try
        {
            List<BookDTO> books = bookService.getBooksList();
            return ResponseEntity.ok(books);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookInfo(@PathVariable Long id)
    {
        try
        {
            BookDTO book = bookService.getBook(id);
            return ResponseEntity.ok(book);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO book)
    {
        try
        {
            BookDTO updatedBook = bookService.updateBookInfo(id, book);
            return ResponseEntity.ok(updatedBook);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id)
    {
        try
        {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book successfully deleted");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}