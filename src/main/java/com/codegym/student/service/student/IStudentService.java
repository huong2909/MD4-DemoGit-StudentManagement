package com.codegym.student.service.student;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import com.codegym.student.service.IGeneralService;

public interface IStudentService extends IGeneralService<Student> {
    Iterable<Student> findAllByClazz(Clazz clazz);

    Iterable<Student> findAllByOrderByMark();
}
