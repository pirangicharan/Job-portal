package com.jobportal.jobportal_backend.controller;

import com.jobportal.jobportal_backend.dto.JobRequest;
import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> postJob(@RequestBody JobRequest jobRequest){
        return new ResponseEntity<>(jobService.postJob(jobRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Job>> getAllJobs(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        return ResponseEntity.ok(jobService.getJobs(title,pageNumber,pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
