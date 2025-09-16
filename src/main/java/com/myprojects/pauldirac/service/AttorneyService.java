package com.myprojects.pauldirac.service;
import com.myprojects.pauldirac.entity.Attorney;
import java.util.List;
import java.util.Optional;

public interface AttorneyService {

    List<Attorney> findAll();
    Attorney findById(int id);
    Attorney save(Attorney employee);
    void deleteById(int id);

}
