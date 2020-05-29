package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetFundInfomationController {
    @Autowired
    private FundService fundService;

    @GetMapping(value = "/fund/{fundId}")
    public Optional<Fund> getInfo(@PathVariable int fundId) throws Exception{
        return fundService.findById(fundId);
    }
}
