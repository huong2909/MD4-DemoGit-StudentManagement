package com.codegym.student.repository;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAllByClazz(Clazz clazz, Pageable pageable);

    Iterable<Student> findAllByOrderByMark();
}
