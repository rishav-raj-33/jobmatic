package Jobmatic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Jobmatic.entity.JobEntity;

public interface JobRepo extends JpaRepository<JobEntity, Integer> {

}
