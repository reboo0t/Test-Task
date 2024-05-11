package com.Bookery.TestTask.repository;

import com.Bookery.TestTask.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByTitle_thenReturnBook() {
        // given
        Book book = new Book();
        book.setTitle("Test Book");
        entityManager.persist(book);
        entityManager.flush();

        // when
        Optional<Book> found = bookRepository.findByTitle(book.getTitle());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getTitle()).isEqualTo(book.getTitle());
    }
}