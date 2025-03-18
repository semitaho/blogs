package fi.tahoo.batch_fetch.bookstore;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
public class Author {

  @Id
  @GeneratedValue
  private Long authorId;

  private String name;

  @BatchSize(size = 5)
  @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
  private List<Book> bookList;

  public Long getAuthorId() {
    return authorId;
  }

  public String getName() {
    return name;
  }

  public List<Book> getBookList() {
    return bookList;
  }

  public void setBookList(final List<Book> bookList) {
    this.bookList = bookList;
  }
}
