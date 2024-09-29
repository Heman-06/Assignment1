package com.backend.Backend.Service;

import com.backend.Backend.Model.College;
import com.backend.Backend.Model.Course;
import com.backend.Backend.Repository.CollegeRepository;
import com.backend.Backend.Repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Transactional
    public Course createCourse(Long collegeId, Course course) {
        College college = collegeRepository.findById(collegeId)
                .orElseThrow(() -> new EntityNotFoundException("College not found with id: " + collegeId));
        course.setCollege(college);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }



    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setName(courseDetails.getName());
            course.setDuration(courseDetails.getDuration());
            course.setCourseFee(courseDetails.getCourseFee());
            return courseRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
