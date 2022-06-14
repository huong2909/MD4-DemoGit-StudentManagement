package com.codegym.student.repository;

import com.codegym.student.model.Clazz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClazzRepository extends PagingAndSortingRepository<Clazz, Long> {
}
