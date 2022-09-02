package com.contestapi.contestapo.Controller;


import com.contestapi.contestapo.Exception.CustomError;
import com.contestapi.contestapo.Model.ContestDataSet;
import com.contestapi.contestapo.Model.CountOfUrlCalls;
import com.contestapi.contestapo.Model.TransactionResponse;
import com.contestapi.contestapo.Service.ContestDatabase;
import com.contestapi.contestapo.Util.AdhocServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contestDataSet")
public class ContestController {
        @Autowired
        private ContestDatabase conn;


        @Autowired
        private AdhocServices adhoc;

        @PostMapping("/addContest")
        public ResponseEntity<TransactionResponse> adddata(@RequestBody ContestDataSet data) throws CustomError {
                Random inter=new Random();
                ContestDataSet value=new ContestDataSet();
                TransactionResponse ab=new TransactionResponse();
                data.setTiming(new Date());
                if(!conn.findById(data.getName()).isPresent()) {
                         value = conn.save(data);

                        ab.setContestDataSet(value);
                        if(conn.findById(data.getName()).isPresent()){
                                ab.setStatus("Succesfully Inserted Data");
                        }else{
                                ab.setStatus("Failed , Please try again");
                        }
                        return ResponseEntity.ok(ab);
                }else{
                        throw new CustomError("Data Already Exists","The entry with Same name already exists");

                }

        }


        @GetMapping("/allData")
        public ResponseEntity<List<ContestDataSet>> getalldata(){
                return ResponseEntity.ok(conn.findAll());
        }

        @GetMapping("/specificContestData")
        public ResponseEntity<ContestDataSet> getSpecificData(@RequestBody(required = false) ContestDataSet data,
        @RequestHeader(required = false) String name
        ) throws CustomError{

                String Value="";
                if(data==null){
                        Value=name;
                }else{
                        Value=data.getName();
                }
                adhoc.storeCount(Value);
                ContestDataSet call=new ContestDataSet();
                if(conn.findById(Value).isPresent()){
                        call=conn.findById(Value).get();
                }else{
                        throw new CustomError("No Contest Avialable","Invalid Contest Name");
                }

                return  ResponseEntity.ok(call);
        }


        @GetMapping("/popularContests")
        public ResponseEntity<List<CountOfUrlCalls>> getCallsInDesc(){

                List<CountOfUrlCalls> out=adhoc.allCalls().stream().sorted(Comparator.comparing(CountOfUrlCalls::getCount).reversed()).collect(Collectors.toList());
               return ResponseEntity.ok(out);
        }


}
