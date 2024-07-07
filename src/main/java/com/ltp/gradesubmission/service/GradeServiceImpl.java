package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.*;
import com.ltp.gradesubmission.exception.*;
import com.ltp.gradesubmission.repository.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService{

    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade =gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
        return unWrapGrade(grade,studentId,courseId);
    }

    @Override
    @Transactional
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course = CourseServiceImpl.unwrapCourse(courseRepository.findById(courseId),courseId);
        if(!student.getCourses().contains(course)) throw new StudentNotEnrolledException(studentId,courseId);
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    @Transactional
    public Grade updateGrade(String score, Long studentId, Long courseId) {
       Optional<Grade> grade =gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
       Grade unWrappedGrade = unWrapGrade(grade,studentId,courseId);
       unWrappedGrade.setScore(score);
        return gradeRepository.save(unWrappedGrade);
    }

    @Override
    @Transactional
    public void deleteGrade(Long studentId, Long courseId) {
            gradeRepository.deleteByStudentIdAndCourseId(studentId,courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }
    static Grade unWrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if(entity.isPresent()) return entity.get();
        else throw new GradeNotFoundException(studentId,courseId);
    }
}
