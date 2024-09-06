package com.poorna.BookStore.controller;

import com.poorna.BookStore.service.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookApiController {

    @Autowired
    private GoogleBooksService googleBooksService;

    public String searchBooks(@RequestParam String query){
        return googleBooksService.searchBooks(query).toString();
    }
}
