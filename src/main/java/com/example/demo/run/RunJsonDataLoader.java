//package com.example.demo.run;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//
//@Component
//public class RunJsonDataLoader implements CommandLineRunner {
//    private static final Logger logger = LoggerFactory.getLogger(RunJsonDataLoader.class);
////    private final RunRepository runRepository;
//    ObjectMapper objectMapper;
//
////    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
////        this.runRepository = runRepository;
////        this.objectMapper = objectMapper;
////    }
//
//    //01:58:02 he is trying to implement command lien runner to add json runs to the h2 database each time the application starts
//    @Override
//    public void run(String... args) throws Exception {
//        logger.info("Loading JSON data...");
//        int count = runRepository.count();
//        logger.info("Run count: {}", count);
//        if (runRepository.count() == 0) {
//            try (InputStream inputStream = RunJsonDataLoader.class.getResourceAsStream("/data/runs.json")) {
//                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
//                logger.info("Reading {} runs from JSON file", allRuns.runs().size());
//                //print all the runs
//                allRuns.runs().forEach(run -> logger.info("Run: {}", run));
//                runRepository.saveAll(allRuns.runs());
//            } catch (Exception e) {
//                logger.error("Error loading JSON data", e);
//            }
//        } else {
//            logger.info("Data already loaded, skipping JSON data load.");
//
//        }
//    }
//}