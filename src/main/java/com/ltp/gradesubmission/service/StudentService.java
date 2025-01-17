package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudent(Long id) ;
    Student saveStudent(Student student);
    List<Student> getStudents();
    void deleteStudent(Long id);
}
