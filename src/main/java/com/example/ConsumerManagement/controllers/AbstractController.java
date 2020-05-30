package com.example.ConsumerManagement.controllers;

import com.example.ConsumerManagement.checker.CheckerDecorator;

public abstract class AbstractController {
    protected CheckerDecorator checker;

    protected void initChecker(){}
    protected void addChecker(CheckerDecorator checker){
        if (this.checker == null) this.checker = checker;
        else this.checker.appendChain(checker);
    }
}
