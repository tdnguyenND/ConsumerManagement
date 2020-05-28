package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class GetFundInfomationController {
    @Autowired
    private FundService fundService;

    @GetMapping(value = "/{fundId}")
    public @ResponseBody Optional<Fund> getInfo(@PathVariable int fundId) throws Exception{
        return fundService.findById(fundId);
    }
}
