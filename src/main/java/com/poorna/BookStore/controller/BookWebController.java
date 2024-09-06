package com.poorna.BookStore.controller;

import com.poorna.BookStore.model.BookDto;
import com.poorna.BookStore.service.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookWebController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @GetMapping("/search")
    public String searchBooks(String query, Model model){
        List<BookDto> books = googleBooksService.searchBooks(query);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping
     public String showSearchPage(){
        return "books";
    }

}
