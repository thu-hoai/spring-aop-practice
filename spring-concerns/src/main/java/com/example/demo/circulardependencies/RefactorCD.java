package com.example.demo.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class RefactorCD {

}

@Service
class CService {
    @Autowired
    private @Lazy DService dService;

    public void test() {
        dService.getFileName();
    }

    public void getAnotherFileName() {
    }
}

@Service
class DService {

    @Autowired
    private CService cService;

    public void getOrderName() {
        cService.getAnotherFileName();
    }

    public String getFileName() {
        return "test";
    }
}
