package com.example.demo.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AService {
//    private BService bService;
//
//    // generate setter
//    @Autowired
//    public void setBService(BService bService) {
//        this.bService = bService;
//    }
}

@Service
class BService {

//    private AService aService;
//
//    // add setter
//    @Autowired
//    public void setAService(AService aService) {
//        this.aService = aService;
//    }

}