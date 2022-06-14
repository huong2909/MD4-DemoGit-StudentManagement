package com.codegym.student.controller;

import com.codegym.student.model.Student;
import com.codegym.student.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    IStudentService studentService;

    @DeleteMapping("{id}")
    public ResponseEntity<Optional<Student>> remove(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/sortByMark")
    public ResponseEntity<Iterable<Student>> findAllByOrderByPrice() {
        Iterable<Student> students = studentService.findAllByOrderByMark();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
