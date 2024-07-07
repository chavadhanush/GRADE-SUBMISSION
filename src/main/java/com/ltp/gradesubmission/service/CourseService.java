package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.*;

import java.util.*;

public interface CourseService {
        Course getCourse(Long courseId);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    Course addStudentToCourse(Long studentId, Long courseId);
    List<Course> getCourses();
    Set<Student> getEnrolledStudents(Long courseId);
}
