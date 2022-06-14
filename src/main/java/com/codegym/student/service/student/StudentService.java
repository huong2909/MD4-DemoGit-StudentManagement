package com.codegym.student.service.student;

import com.codegym.student.model.Student;

import java.util.Optional;

public class StudentService implements IStudentService{
    @Override
    public Iterable<Student> findAll() {
        return null;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
