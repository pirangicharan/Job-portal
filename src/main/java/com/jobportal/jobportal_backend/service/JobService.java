package com.jobportal.jobportal_backend.service;

import com.jobportal.jobportal_backend.dto.JobRequest;
import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job postJob(JobRequest jobRequest){
        Job job = new Job();
        job.setCompany(jobRequest.getCompany());
        job.setDescription(jobRequest.getDescription());
        job.setSalary(jobRequest.getSalary());
        job.setLocation(jobRequest.getLocation());
        job.setTitle(jobRequest.getTitle());
        return jobRepository.save(job);
    }

    public Page<Job> getJobs(String title, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (title != null && !title.isBlank()){
            return jobRepository.findByTitleContainingIgnoreCase(title,pageable);
        }
        return jobRepository.findAll(pageable);
    }

    public Optional<Job> getJobById(Long id){
        return jobRepository.findById(id);
    }
}
