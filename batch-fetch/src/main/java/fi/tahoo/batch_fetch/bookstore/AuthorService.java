package fi.tahoo.batch_fetch.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Transactional(readOnly = true)
  public List<AuthorAndBooks> getAuthorsAndBooks() {
    return authorRepository.findAll()
            .stream()
            .map(this::convertToAuthorAndBooks)
            .toList();
  }

  private AuthorAndBooks convertToAuthorAndBooks(Author author) {
    return new AuthorAndBooks(author.getAuthorId(),
            author.getName(),
            author.getBookList().stream().map(Book::getName).toList());
  }
}
