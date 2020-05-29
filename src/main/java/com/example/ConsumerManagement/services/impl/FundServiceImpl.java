package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.models.repositories.FundRepository;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("fundService")
@Transactional
public class FundServiceImpl implements FundService {
    @Autowired
    FundRepository fundRepository;

    @Override
    public Optional<Fund> findById(Integer integer) {
        return fundRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        fundRepository.deleteById(integer);
    }

    @Override
    public <S extends Fund> S save(S s) {
        return fundRepository.save(s);
    }

    @Override
    public String findOwnerByFundId(Integer fundId) {
        return fundRepository.findOwnerByFundId(fundId);
    }

    @Override
    public void changeOwner(Integer fundId, String newOwner) {
        fundRepository.changeOwner(fundId, newOwner);
    }

    @Override
    public boolean isExist(Integer id) {
        return fundRepository.existsById(id);
    }
}
