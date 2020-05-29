package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import com.example.ConsumerManagement.models.repositories.UserFundRepository;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userFundService")
@Transactional
public class UserFundServiceImpl implements UserFundService {
    @Autowired
    UserFundRepository userFundRepository;

    @Override
    public Iterable<String> findAllMembersByFundId(int fundId) {
        return userFundRepository.findAllMemberByFundId(fundId);
    }

    @Override
    public Iterable<Integer> findAllFundIdByUsername(String username) {
        return userFundRepository.findAllFundIdByUsername(username);
    }

    @Override
    public <S extends UserFund> S save(S s) {
        return userFundRepository.save(s);
    }

    @Override
    public void deleteById(UserFundKey userFundKey) {
        userFundRepository.deleteById(userFundKey);
    }

    @Override
    public void deleteAllByFundId(int fundId) {
        userFundRepository.deleteAllByFundId(fundId);
    }
}
