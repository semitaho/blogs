package fi.tahoo.batch_fetch.bookstore;

import fi.tahoo.batch_fetch.BatchFetchApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

  private static Logger LOG = LoggerFactory.getLogger(BatchFetchApplication.class);

  private final AuthorRepository authorRepository;

  AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Transactional(readOnly = true)
  public List<AuthorAndBooks> getAuthors() {
    return authorRepository.findAll()
            .stream()

            .map(this::convertToAuthorAndBooks)
            .toList();
  }

  private AuthorAndBooks convertToAuthorAndBooks(Author author) {
    return new AuthorAndBooks(author.getAuthorId(),
            author.getName(),
            author.getBookList().parallelStream().map(Book::getName).toList());
  }
}
