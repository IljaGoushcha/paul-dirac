package com.myprojects.pauldirac.service;

import com.myprojects.pauldirac.dao.AttorneyRepository;
import com.myprojects.pauldirac.entity.Attorney;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttorneyServiceImpl implements AttorneyService {

    private final AttorneyRepository attorneyRepository;

    public AttorneyServiceImpl(AttorneyRepository attorneyRepository) {
        this.attorneyRepository = attorneyRepository;
    }

    @Override
    public List<Attorney> findAll() {
        return attorneyRepository.findAll();
    }

    @Override
    public Attorney findById(int id) {

        Optional<Attorney> result = attorneyRepository.findById(id);
        Attorney myAttorney = null;

        if (result.isPresent()) {
            myAttorney = result.get();
        } else {
            throw new RuntimeException("Did not find Attorney with id =" + id);
        }

        return myAttorney;
    }

    @Override
    public Attorney save(Attorney employee) {
        return attorneyRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        attorneyRepository.deleteById(id);
    }
}
