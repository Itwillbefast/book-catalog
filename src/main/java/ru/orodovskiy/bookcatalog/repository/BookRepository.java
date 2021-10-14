package ru.orodovskiy.bookcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orodovskiy.bookcatalog.model.Book;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Stream<Book> streamAllByNameStartsWithIgnoreCaseAndAuthorOrderByName(String name, String author);

    Optional<Book> findByIsbn(String isbn);
}
