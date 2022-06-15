package com.codegym.student.repository;

import com.codegym.student.model.Clazz;
import com.codegym.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAllByClazz(Clazz clazz, Pageable pageable);

    Iterable<Student> findAllByOrderByMark();

    Iterable<Student> findAllByNameContaining(String name);

    Iterable<Student> findAllByMarkBetween(double from, double to);

    @Query(value = " select * from students order by id LIMIT 3", nativeQuery = true)
    Iterable<Student>findTop3Students();

}
