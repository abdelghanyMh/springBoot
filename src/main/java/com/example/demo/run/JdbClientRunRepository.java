package com.example.demo.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

// according to the mentor the controller should only get request return response
//that why he creates RunRepository to delegate the manging of the model to this class
// repository data access pattern
@Repository
public class JdbClientRunRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbClientRunRepository.class);

    private final JdbcClient jdbcClient;

    // spring will automatically inject despondency to your constructor you don't need to add it your self
// for example here spring wil  add the instance of jsbc client
    public JdbClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM RUN").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM RUN WHERE id = :id")
                .params("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("Insert Into Run(id,title,started_on,completed_on,miles,location,version) VALUES(?,?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), run.version()))
                .update();
//        update() returns how many rows are updated in the database


        Assert.state(updated == 1, "Run not created: " + run.title()); // this will throw an exception if the condition is not met
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE Run SET title = :title, started_on = :startedOn, completed_on = :completedOn, miles = :miles, location = :location WHERE id = :id")
                .params("title", run.title())
                .params("startedOn", run.startedOn())
                .params("completedOn", run.completedOn())
                .params("miles", run.miles())
                .params("location", run.location())
                .params("id", id)
                .params("version", run.version())
                .update();
        Assert.state(updated == 1, "Run not updated: " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                .params("id", id)
                .update();
        Assert.state(updated == 1, "Run not deleted: " + id);
    }

    public int count() {
        logger.info("Counting runs...");
        return jdbcClient.sql("SELECT COUNT(*) FROM Run")
                .query((rs, rowNum) -> rs.getInt(1))
                .stream()
                .findFirst()
                .orElse(0);

    }

    public List<Run> findByLocation(Location location) {
        return jdbcClient.sql("SELECT * FROM Run WHERE location = :location")
                .params("location", location)
                .query(Run.class)
                .list();
    }

    public void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }
}
