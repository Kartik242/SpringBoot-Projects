package com.learning.JobApp.repo;

import com.learning.JobApp.model.JobPost;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JobRepo {

    List<JobPost> jobs = new ArrayList<>(Arrays.asList(
            new JobPost(1, "Java Developer", "asfasf", 1, List.of("java"))

    ));


    public List<JobPost> getAllJobs() {
        return jobs;
    }

    public void addJob(JobPost job) {
        jobs.add(job);
    }
}
