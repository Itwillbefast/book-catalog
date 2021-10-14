package ru.orodovskiy.bookcatalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.orodovskiy.bookcatalog.dto.AckDto;
import ru.orodovskiy.bookcatalog.dto.BookDto;
import ru.orodovskiy.bookcatalog.model.Book;
import ru.orodovskiy.bookcatalog.service.BookService;
import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> getBooksByNameAndAuthor(@RequestParam String name,
                                                 @RequestParam String author) {

        return bookService.getBooksByNameAndAuthor(name, author);
    }


    @PostMapping("/books")
    public BookDto createBook(@Valid @RequestBody Book book)
                                                 {
        return bookService.createBook(book);
    }

    @PatchMapping("/books/{id}")
    public BookDto updateBook(@PathVariable Long id,
                                @Valid @RequestBody Book book) {

        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public AckDto deleteBook(@PathVariable Long id) {

        return bookService.deleteBook(id);
    }
}
