package com.codegym.student.controller;

import com.codegym.student.model.Student;
import com.codegym.student.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    IStudentService studentService;

    //    @GetMapping("")
//    public ResponseEntity<Iterable<Student>> findAll() {
//        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<Iterable<Student>> findAllStudent(@PageableDefault(size = 2) Pageable pageable) {
        Page<Student> students = studentService.findAll(pageable);

        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/sortByMark")
    public ResponseEntity<Iterable<Student>> findAllByOrderByPrice() {
        Iterable<Student> students = studentService.findAllByOrderByMark();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Student> add(@RequestParam("file") MultipartFile file, Student student) {
        String fileName = file.getOriginalFilename();
        student.setImage(fileName);
        try {
            file.transferTo(new File("D:\\BT-MD4\\MD4-Demogit-StudentManagement\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(studentService.save(student));
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Student>> findAllByNameContaining(@RequestParam String name) {
        return new ResponseEntity<>(studentService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/find-between")
    public ResponseEntity<Iterable<Student>> findAllByMarkBetween(@RequestParam double from, double to) {
        return new ResponseEntity<>(studentService.findAllByMarkBetween(from, to), HttpStatus.OK);
    }

    @GetMapping("/find-top-3")
    public ResponseEntity<Iterable<Student>> findTop3Students() {
        return new ResponseEntity<>(studentService.findTop3Students(), HttpStatus.OK);
    }

}
