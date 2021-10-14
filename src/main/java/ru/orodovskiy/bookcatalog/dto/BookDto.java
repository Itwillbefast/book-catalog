package ru.orodovskiy.bookcatalog.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class BookDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String author;

    private Long theYearOfPublishing;

    @NotEmpty
    private String isbn;

}
