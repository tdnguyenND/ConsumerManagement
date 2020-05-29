package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteFundController {
    @Autowired
    private FundService fundService;

    @DeleteMapping(value = "/fund/{fundId}")
    public void delete(@PathVariable int fundId) throws Exception{
        fundService.deleteById(fundId);
    }
}
