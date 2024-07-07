package com.ltp.gradesubmission.exception;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long studentId, Long courseId) {
        super("The student with " + studentId + " and course with " + courseId + " was not found in our records.");
    }
}
