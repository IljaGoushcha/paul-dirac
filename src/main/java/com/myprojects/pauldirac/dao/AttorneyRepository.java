package com.myprojects.pauldirac.dao;

import com.myprojects.pauldirac.entity.Attorney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttorneyRepository extends JpaRepository<Attorney, Integer> {}
