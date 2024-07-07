package com.ltp.gradesubmission.exception;

public class StudentNotEnrolledException extends RuntimeException{
    public StudentNotEnrolledException(Long studentId,Long courseId){
        super(" The student with " + studentId + " is not enrolled with course " + courseId);
    }
}
