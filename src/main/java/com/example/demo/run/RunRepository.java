package com.example.demo.run;

import com.example.demo.DemoApplication;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// according to the mentor the controller should only get request return response
//that why he creates RunRepository to delegate the manging of the model to this class
// repository data access pattern
@Repository
public class RunRepository {
    public static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
private final JdbcClient jdbcClient;

// spring will automatically inject despondency to your constructor you don't need to add it your self
// for example here spring wil  add the instance of jsbc client
    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM RUN").query(Run.class).list();
    }
}
