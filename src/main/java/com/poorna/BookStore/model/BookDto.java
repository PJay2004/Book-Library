package com.poorna.BookStore.model;

import lombok.Getter;

@Getter
public class BookDto {
    private String title;
    private String authors;
    private String description;
    private String thumbnail;

    // Getters and Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
