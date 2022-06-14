package com.codegym.student.repository;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student,Long> {
    Iterable<Student> findAllByClazz(Clazz clazz);

    Iterable<Student> findAllByOrderByMark();
}
