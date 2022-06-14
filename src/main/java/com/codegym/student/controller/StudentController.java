package com.codegym.student.controller;

import com.codegym.student.model.Student;
import com.codegym.student.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
        if (!student.isPresent()) {
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

    @PostMapping("/upload")
    public ResponseEntity<Student> handleFileUpload(@RequestParam("file") MultipartFile file, Student student) {
        String fileName = file.getOriginalFilename();
        student.setImage(fileName);
        try {
            file.transferTo(new File("G:\\Code gym\\Module 4\\MD4-DemoGit-StudentManagement\\img\\" + fileName));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(studentService.save(student));
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Student>> findAllByNameContaining(@RequestParam String name) {
//        List<Student> searchList = (List<Student>) studentService.findAllByNameContaining(name);
//        if (searchList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(studentService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/find-between")
    public ResponseEntity<Iterable<Student>> findAllByMarkBetween(@RequestParam double from, double to){
      return new ResponseEntity<>(studentService.findAllByMarkBetween(from, to), HttpStatus.OK);
    }

    @GetMapping("/find-top-3")
    public ResponseEntity<Iterable<Student>> findTop3Students(){
        return new ResponseEntity<>(studentService.findTop3Students(),HttpStatus.OK);
    }

}
