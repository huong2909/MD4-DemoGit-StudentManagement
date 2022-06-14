package com.codegym.student.service.student;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import com.codegym.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;

public class StudentService implements IStudentService{
@Autowired
    IStudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findAllByClazz(Clazz clazz) {
        return studentRepository.findAllByClazz(clazz);
    }

    @Override
    public Iterable<Student> findAllByOrderByMark() {
        return studentRepository.findAllByOrderByMark();
    }
}
