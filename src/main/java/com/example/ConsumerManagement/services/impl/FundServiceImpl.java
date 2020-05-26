package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.models.repositories.FundRepository;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fundService")
public class FundServiceImpl implements FundService {
    @Autowired
    FundRepository fundRepository;

    @Override
    public Optional<Fund> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public <S extends Fund> S save(S s) {
        return null;
    }

    @Override
    public String findOwnerByFundId(Integer fundId) {
        return null;
    }

    @Override
    public void changeOwner(Integer fundId, String newOwner) {

    }
}
