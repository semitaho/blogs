package fi.tahoo.batch_fetch.bookstore;

import java.util.List;

public record AuthorAndBooks(Long authorId, String authorName, List<String> books) {
}
