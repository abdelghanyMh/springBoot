package com.example.demo.run;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs;

    public List<Run> findAll() {
        return runs;
    }

    public Optional<Run> findById(Integer id) {
        return runs.stream().filter(r -> r.id() == id).findFirst();
    }

    public void save(Run run) {
        System.out.println(run);
        runs.add(run);
    }

    public void deleteById(Integer id) {
        runs.removeIf(r -> r.id() == id);
    }

    public void update(Run run,Integer id) {
        Optional<Run> existingRun = findById(id);
        if(existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()),run);
        }
    }

    public int count() {
        return runs.size();
    }

    @PostConstruct
    private void init() {
        runs = new ArrayList<Run>();
        runs.add(new Run(1, "Run 1", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 10, Location.INDOOR));
        runs.add(new Run(2, "Run 2", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 20, Location.OUTDOOR));
    }
}
