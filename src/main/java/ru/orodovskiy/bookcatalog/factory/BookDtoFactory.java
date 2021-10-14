package ru.orodovskiy.bookcatalog.factory;

import org.springframework.stereotype.Component;
import ru.orodovskiy.bookcatalog.dto.BookDto;
import ru.orodovskiy.bookcatalog.model.Book;

@Component
public class BookDtoFactory {

    public BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .theYearOfPublishing(book.getTheYearOfPublishing())
                .isbn(book.getIsbn())
                .build();
    }
}
