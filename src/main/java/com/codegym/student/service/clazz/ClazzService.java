package com.codegym.student.service.clazz;

import com.codegym.student.model.Clazz;

import java.util.Optional;

public class ClazzService implements IClazzService{
    @Override
    public Iterable<Clazz> findAll() {
        return null;
    }

    @Override
    public Optional<Clazz> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Clazz save(Clazz clazz) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
