package fi.tahoo.batch_fetch;

import fi.tahoo.batch_fetch.bookstore.Author;
import fi.tahoo.batch_fetch.bookstore.AuthorRepository;
import fi.tahoo.batch_fetch.bookstore.AuthorService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BatchFetchApplication {

	private static Logger LOG = LoggerFactory.getLogger(BatchFetchApplication.class);


	@Autowired
	private  AuthorRepository authorRepository;


	public static void main(String[] args) {

		final var ctx = SpringApplication.run(BatchFetchApplication.class, args);
		final var service  =ctx.getBean(AuthorService.class);
	  final var authorsAndBooks = service.getAuthors();
		LOG.info("authors and books : {}",authorsAndBooks);


	}

}
