package com.example.demo.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRunRepositoryTest {
    InMemoryRunRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRunRepository();
        // init function will never call cuz spingBoot is not running dung test
        // so we have to manually add data to the repository
        repository.create(new Run(1, "Morning Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 5, Location.INDOOR, 0));
        repository.create(new Run(2, "Evening Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 10, Location.OUTDOOR, 0));
    }

    @Test
    void shoudFindAll() {
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size(), "Should return 2 runs");
    }

    @Test
    void shouldFindRunByValidId() {
        Run run = repository.findById(1).orElseThrow();
        assertEquals(1, run.id(), "Should return run with id 1");
        assertEquals("Morning Run", run.title(), "Should return run with title Morning Run");
    }

    @Test
    void shouldNotFindRunByInvalidId() {
        assertThrows(RunNotFoundException.class, () -> repository.findById(3).orElseThrow());
    }

    @Test
    void shouldCreateNewRun() {
        Run newRun = new Run(3, "Afternoon Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 10, Location.OUTDOOR, 0);
        repository.create(newRun);
        assertEquals(3, repository.count(), "Should return 3 runs");
    }

    @Test
    void shouldUpdateRun() {
        Run newRun = new Run(2, "Evening Run", LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 10, Location.OUTDOOR, 1);
        repository.update(newRun, 2);
        Run updatedRun = repository.findById(2).orElseThrow();
        assertEquals(1, updatedRun.version(), "Should return updated version");
        assertEquals("Evening Run", updatedRun.title(), "Should return updated title");
    }

    @Test
    void shoudDeleteRun() {
        repository.delete(1);
        assertEquals(1, repository.count(), "Should return 1 run");
    }
}