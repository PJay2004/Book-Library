package com.poorna.BookStore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poorna.BookStore.model.BookDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksService {

    @Value("${google.books.api.key}")
    private String apikey;

    private static final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes";

    public List<BookDto> searchBooks(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(GOOGLE_BOOKS_API_URL)
                .queryParam("q", query)
                .queryParam("key", apikey)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);

        // Parse JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookDto> books = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                BookDto book = new BookDto();
                book.setTitle(item.path("volumeInfo").path("title").asText());
                book.setAuthors(item.path("volumeInfo").path("authors").toString());
                book.setDescription(item.path("volumeInfo").path("description").asText());
                book.setThumbnail(item.path("volumeInfo").path("imageLinks").path("thumbnail").asText());
                books.add(book);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
