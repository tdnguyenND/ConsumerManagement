package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;

public interface UserFundService {
    Iterable<String> findAllMembersByFundId(int fundId);

    Iterable<Integer> findAllFundIdByUsername(String username);

    <S extends UserFund> S save(S s);

    void deleteById(UserFundKey userFundKey);

    void deleteAllByFundId(int fundId);
}
