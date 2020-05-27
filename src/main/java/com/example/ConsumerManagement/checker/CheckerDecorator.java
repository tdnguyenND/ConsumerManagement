package com.example.ConsumerManagement.checker;

public abstract class CheckerDecorator implements Checker {
    private Checker chain;

    public void setChain(Checker chain) {
        this.chain = chain;
    }

    @Override
    public boolean satisfy() {
        if (chain == null) return true;
        return chain.satisfy();
    }
}
