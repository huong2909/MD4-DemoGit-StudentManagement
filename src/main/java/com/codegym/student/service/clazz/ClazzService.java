package com.codegym.student.service.clazz;

import com.codegym.student.model.Clazz;
import com.codegym.student.repository.IClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClazzService implements IClazzService{

    @Autowired
    public IClazzRepository clazzRepository;

    @Override
    public Iterable<Clazz> findAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Optional<Clazz> findById(Long id) {
        return clazzRepository.findById(id);
    }

    @Override
    public Clazz save(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public void remove(Long id) {
        clazzRepository.deleteById(id);
    }
}
