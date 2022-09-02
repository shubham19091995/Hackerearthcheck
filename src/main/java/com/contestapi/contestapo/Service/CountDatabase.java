package com.contestapi.contestapo.Service;

import com.contestapi.contestapo.Model.CountOfUrlCalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;


@Controller
public interface CountDatabase extends JpaRepository<CountOfUrlCalls,String> {
}
