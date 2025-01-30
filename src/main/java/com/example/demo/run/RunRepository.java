package com.example.demo.run;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

//run id type is integer
public interface RunRepository extends ListCrudRepository<Run, Integer> {
    List<Run> findAllByLocation(String location);
}
