package com.example.demo.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> runs() {
        return runRepository.findAll();
    }
//
//    @GetMapping("/{id}")
//    Run find(@PathVariable String id) {
//        Optional<Run> run = runRepository.findById(Integer.parseInt(id));
//        if (run.isEmpty()) {
//            throw new RunNotFoundException(Integer.parseInt(id));
//        }
//        return run.get();
//    }
//
//    @GetMapping("/count")
//    int count() {
//        return runRepository.count();
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("")
//    Run create(@Valid @RequestBody Run run) {
//        runRepository.save(run);
//        if (run.id() == null) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return run;
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/{id}")
//    Run update(@RequestBody Run run,@PathVariable String id) {
//        runRepository.update(run,Integer.parseInt(id));
//        if (run.id() == null) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return run;
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable String id) {
//        runRepository.deleteById(Integer.parseInt(id));
//    }
//
//    @GetMapping("/hello")
//    String hello() {
//        return "Hello World";
//    }

}
