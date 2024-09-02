package com.kartikeye.SpringDataRest.repo;


import com.kartikeye.SpringDataRest.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface JobRepo extends JpaRepository<JobPost, Integer> {

}

