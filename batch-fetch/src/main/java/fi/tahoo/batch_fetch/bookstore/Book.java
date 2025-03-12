package fi.tahoo.batch_fetch.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

  @Id
  @GeneratedValue
  private Long bookId;

  private String name;

  @ManyToOne
  @JoinColumn(name = "AUTHOR_ID")
  private Author author;

  public String getName() {
    return name;
  }
}
