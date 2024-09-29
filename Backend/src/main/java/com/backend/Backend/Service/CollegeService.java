package com.backend.Backend.Service;

import com.backend.Backend.Model.College;
import com.backend.Backend.Repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {


    @Autowired
    private CollegeRepository collegeRepository;

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id).orElse(null);
    }

    public College createCollege(College college) {
        return collegeRepository.save(college);
    }

    public College updateCollege(Long id, College collegeDetails) {
        College college = collegeRepository.findById(id).orElse(null);
        if (college != null) {
            college.setName(collegeDetails.getName());
            college.setCourses(collegeDetails.getCourses());
            return collegeRepository.save(college);
        }
        return null;
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }


    public College getCollegeDetails(Long id) {
        return collegeRepository.findById(id).orElseThrow(() -> new RuntimeException("College not found"));
    }
}
