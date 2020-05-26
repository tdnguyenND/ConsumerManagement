package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import com.example.ConsumerManagement.models.repositories.UserFundRepository;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userFundService")
public class UserFundServiceImpl implements UserFundService {
    @Autowired
    UserFundRepository userFundRepository;

    @Override
    public Iterable<String> findAllMembersByFundId(int fundId) {
        return null;
    }

    @Override
    public Iterable<Integer> findAllFundIdByUsername(String username) {
        return null;
    }

    @Override
    public <S extends UserFund> S save(S s) {
        return null;
    }

    @Override
    public void deleteById(UserFundKey userFundKey) {

    }

    @Override
    public void deleteAllByFundId(int fundId) {

    }
}
