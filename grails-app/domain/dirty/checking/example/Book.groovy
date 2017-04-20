package dirty.checking.example

class Book {

    String title
    String author

    static constraints = {
        title nullable: true
        author nullable: true
    }

    void setAuthor(String author) {
        // do some custom logic
        this.author = author
    }
}
