package Packables;

public class Book implements Packable {
    private String author;
    private String bookName;
    private Double weight;

    public Book(String author, String bookName, double weight) {
        setAuthor(author);
        setBookName(bookName);
        setWeight(weight);
    }

    public void setAuthor(String authorName) {
        this.author = authorName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getBookName() {
        return this.bookName;
    }

    public double getWeight() {
        return this.weight;
    }

    public String toString() {
        return author + ": " + bookName;
    }
}
