package com.example.demo.run;

public class RunNotFoundException extends RuntimeException {
    public RunNotFoundException(Integer id) {
        super("Run not Found "+ id);
    }
}
