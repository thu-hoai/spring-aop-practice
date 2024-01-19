package com.example.demo.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Practices {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        // Prepare sample book data
        books.add(new Book("Book 1", Collections.singletonList("Author 1"), 29.99, 4, "Fiction", 2010));
        books.add(new Book("Book 1", Collections.singletonList("Author 2"), 19.99, 3, "Non-Fiction", 2015));
        books.add(new Book("Book 3", List.of("Author 3", "Author 1"), 49.99, 4, "Fiction", 2012));
        books.add(new Book("Book 4", Collections.singletonList("Author 4"), 14.99, 4, "Non-Fiction", 2018));
        books.add(new Book("Book 5", Collections.singletonList("Author 5"), 39.99, 5, "Fiction", 2008));
        books.add(new Book("Book 6", Collections.singletonList("Author 6"), 11.11, 1, "Non-Fiction", 1992));

        //    1. Calculate the total price of all books in the shopping cart using the `map` method
        var totalPrice1 = books.stream().map(Book::getPrice).reduce(0.0, Double::sum);
        System.out.println(totalPrice1);
        var totalPrice = books.stream().mapToDouble(Book::getPrice).sum();
        System.out.println(totalPrice);

        //    Calculate a list of number
        System.out.println(Stream.iterate(1, n -> n + 1).limit(10).mapToInt(num -> num).sum());

        //    2. sort a list of books based on their publication year in ascending order.
        var sortedByPublicationYear = books.stream()
            .sorted(Comparator.comparing(Book::getPublicationYear)).toList();
        System.out.println("sortedByPublicationYear" + sortedByPublicationYear);

        //    3. filter out duplicate book titles from a list of books using the `distinct` method of `Stream`.
        var duplicatedBookTitles = books.stream().map(Book::getTitle).distinct().toList();
        System.out.println(duplicatedBookTitles);

        //    4. convert a list of book ratings from a scale of 1-5 to a scale of 1-10
        var convertedRating = books.stream().map(book -> {
            Book newBook = new Book();
            newBook.setTitle(book.getTitle());
            newBook.setAuthors(book.getAuthors());
            newBook.setPrice(book.getPrice());
            newBook.setRating(book.getRating() * 2);
            return newBook;
        }).toList();
        System.out.println("convertedRating" + convertedRating);

        //    5. find the average rating of all books in the bookstore
        var averageRating = books.stream().mapToInt(Book::getRating).average().getAsDouble();
        var averageRating3 = books.stream().mapToInt(Book::getRating).reduce(0, (a, b) -> a + b) / books.size();
        var averageRating4 = books.stream().mapToInt(Book::getRating).reduce(0, Integer::sum) / books.size();
        System.out.println(averageRating);

        //    6. Count the number of fiction books in a given list of books using the `split` method.
        var fictionBooksCount = books.stream()
            .filter(book -> book.getGenre().equalsIgnoreCase("Fiction"))
            .count();
        System.out.println(fictionBooksCount);

        //    8. Concatenate all book titles in a list using the `reduce` operation of `Stream`.
        String titleConcatenate = books.stream().map(Book::getTitle)
            .reduce((str1, str2) -> str1 + ", " + str2)
            .orElse("");
        System.out.println(titleConcatenate);
        String titleConcatenate2 = books.stream().map(Book::getTitle).collect(Collectors.joining(", "));
        System.out.println(titleConcatenate2);

        //    7. Find the book with the lowest price in a list of books using the min method of `Stream`.
        Optional<Book> lowestPriceBook = books.stream().min(Comparator.comparing(Book::getPrice));
        System.out.println(lowestPriceBook);

        //    9. Find the book with the highest price in a list of books using the max method of `Stream`
        Optional<Book> highestPriceBook = books.stream().max(Comparator.comparing(Book::getPrice));
        System.out.println(highestPriceBook);

        //    10. Use the `flatMap` operation to extract all authors from a list of books into a single list.
        List<String> authorsList = books.stream().flatMap(book -> book.getAuthors().stream()).distinct().toList();
        System.out.println(authorsList);

        //    11. check if a given book title is available in the bookstore using the `stream` API.
        boolean isBookAvailable = books.stream().anyMatch(book -> book.getTitle().equalsIgnoreCase("Book 1"));
        System.out.println(isBookAvailable);

        //    13. Use the `peek` operation to print the details (title, author, price) of all books while calculating the total revenue.
        var totalPrices = books.stream().peek(book -> System.out.println(book.getTitle())).mapToDouble(Book::getPrice).sum();
        System.out.println(totalPrices);

        //    15. find the second highest rated book in a list of books using the `sorted` and `skip` methods
        Optional<Book> secondHighestRatedBook = books.stream().sorted(Comparator.comparing(Book::getRating)
              .thenComparing(Book::getTitle))
              .skip(1).findFirst();
        System.out.println("secondHighestRatedBook" + secondHighestRatedBook.get());

        //    14. find the second highest rated book in a list of books using the `sorted` and `limit` methods
        Optional<Book> secondHighestRatedBook2 = books.stream().sorted(Comparator.comparing(Book::getRating)
              .thenComparing(Book::getTitle))
              .limit(1).findFirst();
        System.out.println("secondHighestRatedBook2" + secondHighestRatedBook2.get());

        //    15. find the second highest rated book in a list of books using the `sorted` and `skip` methods
        var secondHighestRatedBook3 = books.stream().sorted(Comparator.comparing(Book::getRating)
                .thenComparing(Book::getTitle))
            .skip(books.size() - 2).findFirst();
        System.out.println("secondHighestRatedBook" + secondHighestRatedBook3.get());

        //    16. Use the `anyMatch` operation to check if a list contains any books with a rating above 4.5.
        var filterByRating = books.stream().anyMatch(book -> book.getRating() > 4.5);
        System.out.println(filterByRating);

        //    17. group a list of books by their genre using the `groupingBy` collector.
        var booksGrouping = books.stream().collect(Collectors.groupingBy(Book::getGenre));
        System.out.println(booksGrouping);

        //    18. calculate the total price of books in a given genre using the `reduce` operation.
        var totalPriceByReduce = books.stream().filter(book -> book.getGenre().equalsIgnoreCase("Fiction"))
            .map(Book::getPrice)
            .reduce(Double::sum).orElse((double) 0);
        System.out.println(totalPriceByReduce);

        //    19. Use the `takeWhile` operation to extract all books published before a specific year from a list of books.
        List<Book> publishedBefore2012Books1 = books.stream().filter(book -> book.getPublicationYear() < 2012).toList();
        List<Book> publishedBefore2012Books2 = books.stream().sorted(Comparator.comparing(Book::getPublicationYear)
                .thenComparing(Book::getTitle))
            .takeWhile(book -> book.getPublicationYear() < 2012).toList();
        System.out.println(publishedBefore2012Books1);
        System.out.println(publishedBefore2012Books2);

        //    20.Convert a list of books into a formatted string with their titles and authors using the `collect` operation.
        var concatTitleAndAuthor = books.stream().map(book -> book.getTitle() + book.getAuthors())
            .collect(Collectors.joining(","));
        System.out.println(concatTitleAndAuthor);

        // remove the duplicate elements from the list
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");

        System.out.println(list);
        System.out.println(list.stream().distinct().collect(Collectors.toList()));
    }
}

