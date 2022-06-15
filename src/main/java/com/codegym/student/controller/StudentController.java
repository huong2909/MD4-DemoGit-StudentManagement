package com.codegym.student.controller;

import com.codegym.student.model.Student;
import com.codegym.student.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    IStudentService studentService;
    @GetMapping("")
    public ResponseEntity<Iterable<Student>> findAll(){
return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Student> add( @RequestParam("file") MultipartFile file,@Valid  @ModelAttribute Student student) {
        String fileName = file.getOriginalFilename();
        student.setImage(fileName);
        try {
            file.transferTo(new File("D:\\SMALL-PROJECT-STUDENT-MANAGER\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(studentService.save(student));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id,@RequestBody Student student ){
        Optional<Student> student1=studentService.findById(id);
        if (!student1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(student1.get().getId());
        studentService.save(student);
        return new ResponseEntity<>(student1.get(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/edit/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }
}
