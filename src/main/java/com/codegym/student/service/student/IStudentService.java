package com.codegym.student.service.student;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import com.codegym.student.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGeneralService<Student> {
    Page<Student> findAllByClazz(Clazz clazz,Pageable pageable);

    Iterable<Student> findAllByOrderByMark();

    Page<Student> findAll(Pageable pageable);
}
