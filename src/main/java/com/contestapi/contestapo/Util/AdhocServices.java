package com.contestapi.contestapo.Util;


import com.contestapi.contestapo.Model.CountOfUrlCalls;
import com.contestapi.contestapo.Service.CountDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdhocServices {


    @Autowired
    private CountDatabase database;


    public void storeCount(String name){

        CountOfUrlCalls ab=new CountOfUrlCalls();
        if(database.findById(name).isPresent()){
            ab=database.findById(name).get();
            ab.setCount(ab.getCount()+1);
            database.save(ab);
        }else{
            ab.setName(name);
            ab.setCount(1);
            database.save(ab);
        }
    }

    public List<CountOfUrlCalls> allCalls(){
        return database.findAll();
    }
}
