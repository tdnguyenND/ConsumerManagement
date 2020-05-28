package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public @ResponseBody class DeleteFundController {
    @Autowired
    private FundService fundService;

    @DeleteMapping(value = "/{fundId}")
    public void delete(@PathVariable int fundId) throws Exception{
        fundService.deleteById(fundId);
    }
}
