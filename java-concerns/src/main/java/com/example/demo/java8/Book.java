package com.example.demo.java8;

import java.util.List;

public class Book {
    private String title;
    private List<String> authors;
    private double price;
    private int rating;
    private String genre;
    private Integer publicationYear;

    public Book() {
    }

    // constructor for all properties
    public Book(String title, List<String> authors, double price, int rating, String genre, Integer publicationYear) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.rating = rating;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // generate getter and setter for all properties
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + authors + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
