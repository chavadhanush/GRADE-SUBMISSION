package com.ltp.gradesubmission.controller;

import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.service.GradeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {
    private GradeService gradeService;

    @GetMapping("/student/studentId/course/courseId")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId,@PathVariable Long courseId) {
        Grade grade = gradeService.getGrade(studentId, courseId);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.saveGrade(grade,studentId,courseId), HttpStatus.CREATED);
    }
    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.updateGrade(grade.getScore(),studentId,courseId),HttpStatus.OK);
    }
    @DeleteMapping("/student/studentId/course/courseId")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable Long studentId,@PathVariable Long courseId) {
        gradeService.deleteGrade(studentId,courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getAllGrade() {
        return new ResponseEntity<>(gradeService.getAllGrades(), HttpStatus.OK);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId),HttpStatus.OK);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.getCourseGrades(courseId),HttpStatus.OK);
    }
}
