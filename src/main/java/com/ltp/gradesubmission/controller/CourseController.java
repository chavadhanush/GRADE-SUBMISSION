package com.ltp.gradesubmission.controller;

import com.ltp.gradesubmission.entity.*;
import com.ltp.gradesubmission.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.getCourse(courseId), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<List<Course>>(courseService.getCourses(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("{studentId}/course/{courseId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId,courseId),HttpStatus.OK);
    }
    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.getEnrolledStudents(courseId),HttpStatus.OK);
    }
}
