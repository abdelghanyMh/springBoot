package com.example.demo.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbClientRunRepository.class) // comment to see what error will be thrown
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // comment to see what error will be thrown
class JdbClientRunRepositoryTest {
    @Autowired
    JdbClientRunRepository repository;

    @BeforeEach
    void setUp() {
        repository.create(new Run(98, "Morning Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 5, Location.INDOOR, 0));
        repository.create(new Run(99, "Evening Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 10, Location.OUTDOOR, 0));
    }

    @Test
    void shoudFindAll() {
        assertEquals(12, repository.findAll().size(), "Should return 2 runs");
    }

    @Test
    void shouldFindRunByValidId() {
        Run run = repository.findById(98).orElseThrow();
        assertEquals(98, run.id(), "Should return run with id 98");
        assertEquals("Morning Run", run.title(), "Should return run with title Morning Run");
    }

    @Test
    void shouldNotFindRunByInvalidId() {
        assertThrows(RunNotFoundException.class, () -> repository.findById(103).orElseThrow());
    }

    @Test
    void shouldCreateNewRun() {
        Run newRun = new Run(100, "Afternoon Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 10, Location.OUTDOOR, 0);
        repository.create(newRun);
        assertEquals(13, repository.findAll().size(), "Should return 3 runs");
    }

    @Test
    void shouldUpdateRun() {
        Run newRun = new Run(99, "Evening Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 10, Location.OUTDOOR, 1);
        repository.update(newRun, 99);
        Run updatedRun = repository.findById(99).orElseThrow();
        assertEquals(1, updatedRun.version(), "Should return updated version");
        assertEquals("Evening Run", updatedRun.title(), "Should return updated title");
    }

    @Test
    void shouldDeleteRun() {
        repository.delete(98);
        assertEquals(1, repository.count(), "Should return 1 run");
    }

}