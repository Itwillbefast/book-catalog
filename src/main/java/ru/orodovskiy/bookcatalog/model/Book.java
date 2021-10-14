package ru.orodovskiy.bookcatalog.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "books")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "The field mustn't be empty")
    private String name;

    @NotEmpty(message = "The field mustn't be empty")
    private String author;

    @Min(value = 1455, message = "The year of publishing must no earlier than 2021")
    @Max(value = 2021, message = "The year of publishing must no later than 2021")
    private Long theYearOfPublishing;

    @NotEmpty
    @Pattern(regexp = "(978|979)\\-(([0-7]|(8[0-9]|9[0-4]))|(((95|96|97|98)[0-9])|(99[0-3]))|(99[4-8][0-9])|(999[0-9][0-9]))\\-[0-9]{2,7}\\-[0-9]{2,6}\\-([0-9]|X)",
            message = "Not valid ISBN code")
    private String isbn;
}
