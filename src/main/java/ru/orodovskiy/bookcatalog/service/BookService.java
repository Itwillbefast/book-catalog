package ru.orodovskiy.bookcatalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.orodovskiy.bookcatalog.dto.AckDto;
import ru.orodovskiy.bookcatalog.dto.BookDto;
import ru.orodovskiy.bookcatalog.exception.BadRequestException;
import ru.orodovskiy.bookcatalog.exception.NotFoundException;
import ru.orodovskiy.bookcatalog.factory.BookDtoFactory;
import ru.orodovskiy.bookcatalog.model.Book;
import ru.orodovskiy.bookcatalog.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookDtoFactory bookDtoFactory;

    public List<BookDto> getBooksByNameAndAuthor (String name,
                                                  String author) {

        Stream<Book> streamBooks = bookRepository
                    .streamAllByNameStartsWithIgnoreCaseAndAuthorOrderByName(name, author);

        return streamBooks.map(bookDtoFactory::toDto).collect(Collectors.toList());
    }



    public BookDto createBook (Book book) {

        bookRepository.findByIsbn(book.getIsbn()).ifPresent(presentBook -> {
            throw new BadRequestException("ISBN code must be unique");
        });

        bookRepository.saveAndFlush(book);

        return bookDtoFactory.toDto(book);
    }

    public BookDto updateBook (Long id,
                               Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Book with id \"%s\" doesn't exists", id)));

        book.setName(updatedBook.getName());
        book.setAuthor(updatedBook.getAuthor());
        book.setTheYearOfPublishing(updatedBook.getTheYearOfPublishing());
        book.setIsbn(updatedBook.getIsbn());

        bookRepository.saveAndFlush(book);

        return bookDtoFactory.toDto(book);
    }

    public AckDto deleteBook(Long id) {

        bookRepository.deleteById(id);

        return AckDto.makeDefault(true);
    }
}
