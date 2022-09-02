package com.contestapi.contestapo.Service;

import com.contestapi.contestapo.Model.ContestDataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public interface ContestDatabase extends JpaRepository<ContestDataSet,String> {
}
