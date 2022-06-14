package com.codegym.student.controller;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import com.codegym.student.service.clazz.IClazzService;
import com.codegym.student.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/clazz")
public class ClazzController {

    @Autowired
    public IClazzService clazzService;

    @Autowired
    public IStudentService studentService;

    @GetMapping
    public ResponseEntity<Iterable<Clazz>> findAllCategory() {
        List<Clazz> clazzes = (List<Clazz>) clazzService.findAll();
        if (clazzes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clazzes, HttpStatus.OK);
    }

    @GetMapping("/view-clazz/{id}")
    public ResponseEntity<Iterable<Student>> getStudentByCategory(@PathVariable("id") Long id){
        Optional<Clazz> clazz = clazzService.findById(id);
        if(!clazz.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Student> students = (List<Student>) studentService.findAllByClazz(clazz.get());
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);

    }

}
