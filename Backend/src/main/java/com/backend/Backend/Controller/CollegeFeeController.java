package com.backend.Backend.Controller;


import com.backend.Backend.Model.Course;
import com.backend.Backend.Model.CourseFee;
import com.backend.Backend.Repository.CourseFeeRepository;
import com.backend.Backend.Repository.CourseRepository;
import com.backend.Backend.Service.CollegeFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courseFees")
@CrossOrigin(origins = "http://localhost:3000")
public class CollegeFeeController {

    @Autowired
    private CourseFeeRepository courseFeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CollegeFeeService courseFeeService;

    @PostMapping("/course/{courseId}")
    public CourseFee createCourseFee(@PathVariable Long courseId, @RequestBody CourseFee courseFee) {
        Course course = new Course();
        course.setId(courseId);
        courseFee.setCourse(course);
        return courseFeeService.saveCourseFee(courseFee);
    }

    @GetMapping("/all")
    public List<CourseFee> getAllCourseFees() {
        return courseFeeRepository.findAll();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseFee>> getCourseFeesByCourseId(@PathVariable Long courseId) {
        List<CourseFee> courseFees = courseFeeRepository.findByCourseId(courseId);
        return ResponseEntity.ok(courseFees);
    }

    @PutMapping("/{courseFeeId}")
    public ResponseEntity<CourseFee> updateCourseFee(@PathVariable Long courseFeeId, @RequestBody CourseFee courseFeeDetails) {
        Optional<CourseFee> courseFeeOptional = courseFeeRepository.findById(courseFeeId);

        if (!courseFeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        CourseFee courseFee = courseFeeOptional.get();
        courseFee.setFee(courseFeeDetails.getFee());
        courseFee.setAccommodationType(courseFeeDetails.getAccommodationType());
        courseFee.setAccommodationFee(courseFeeDetails.getAccommodationFee());

        CourseFee updatedCourseFee = courseFeeRepository.save(courseFee);

        return ResponseEntity.ok(updatedCourseFee);
    }

    @DeleteMapping("/{courseFeeId}")
    public ResponseEntity<Void> deleteCourseFee(@PathVariable Long courseFeeId) {
        Optional<CourseFee> courseFeeOptional = courseFeeRepository.findById(courseFeeId);

        if (!courseFeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        courseFeeRepository.deleteById(courseFeeId);

        return ResponseEntity.noContent().build();
    }
}
