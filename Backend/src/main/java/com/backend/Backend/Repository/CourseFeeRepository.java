package com.backend.Backend.Repository;

import com.backend.Backend.Model.CourseFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseFeeRepository extends JpaRepository<CourseFee, Long> {
    List<CourseFee> findByCourseId(Long courseId);

}
