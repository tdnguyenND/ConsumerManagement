package com.example.ConsumerManagement.checker;

public abstract class CheckerDecorator implements Checker {
    private CheckerDecorator chain;

    public void setChain(CheckerDecorator chain) {
        this.chain = chain;
    }

    public void appendChain(CheckerDecorator checker){
        if (this.chain == null) setChain(checker);
        else this.chain.appendChain(checker);
    }

    @Override
    public boolean satisfy() {
        if (chain == null) return true;
        return chain.satisfy();
    }
}
