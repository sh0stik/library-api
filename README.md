# Library
This is the REST application for e-Library.
The Library have a list of users and favorite books collection.
#### API allowing:
* Select user and put label for him that he likes/dislikes certain book;
* Select top 10 favourite books (sum all individual user likes);
* Add new book;
* Edit book;
* Remove book from library;
* Find book by title;
* Find the list of the books by the author;
* Add new user;
* Find all favourite or disliked books by certain user.

## Implementation
The application has two implementations of data access objects:
* with H2 in memory database;
* with HashMap storage.

### Built With
* [Spring Boot framework](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Hibernate](http://hibernate.org)
* [H2 database](http://www.h2database.com/html/main.html)

### Tests performed with
* [Junit](https://junit.org/junit4/)
* [Mockito](https://site.mockito.org/)

### To run the application:
* please sure, that you have install [maven](http://maven.apache.org/download.cgi) on your machine
* in command line go to directory with application \library-api\
* in command line type: mvn spring-boot:run

#### Author:
* Daria Shostak