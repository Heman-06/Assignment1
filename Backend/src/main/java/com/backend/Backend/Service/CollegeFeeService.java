package com.backend.Backend.Service;

import com.backend.Backend.Model.CourseFee;
import com.backend.Backend.Repository.CourseFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeFeeService {


    @Autowired
    private CourseFeeRepository courseFeeRepository;

    public List<CourseFee> getAllCourseFees() {
        return courseFeeRepository.findAll();
    }

    public CourseFee getCourseFeeById(Long id) {
        return courseFeeRepository.findById(id).orElse(null);
    }

    public CourseFee saveCourseFee(CourseFee courseFee) {
        return courseFeeRepository.save(courseFee);
    }

    public CourseFee createCourseFee(CourseFee courseFee) {
        return courseFeeRepository.save(courseFee);
    }

    public CourseFee updateCourseFee(Long id, CourseFee courseFeeDetails) {
        CourseFee courseFee = courseFeeRepository.findById(id).orElse(null);
        if (courseFee != null) {
            courseFee.setFee(courseFeeDetails.getFee());
            courseFee.setAccommodationType(courseFeeDetails.getAccommodationType());
            courseFee.setAccommodationFee(courseFeeDetails.getAccommodationFee());
            return courseFeeRepository.save(courseFee);
        }
        return null;
    }

    public void deleteCourseFee(Long id) {
        courseFeeRepository.deleteById(id);
    }
}
