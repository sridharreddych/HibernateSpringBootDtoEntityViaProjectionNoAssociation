package com.bookstore;

import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.dto.BookstoreDto;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            List<BookstoreDto> authors = bookstoreService.fetchAuthors();
            authors.forEach(a -> System.out.println(a.getAuthor() + ", Title: " + a.getTitle()));
        };
    }
}

/*
 * 
 * Entity Inside Spring Projection (no association)

Description: If, for some reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, Author and Book, that have no materialized association between them, but, they share the genre attribute. We use this attribute to join authors with books via JPQL. And, we want to fetch in a Spring projection the authors as entities, Author, and the title of the books.

Key points:

define two unrelated entities (e.g., Author and Book)
define the proper Spring projection having public Author getAuthor() and public String getTitle()
write a JPQL to fetch data

 */
