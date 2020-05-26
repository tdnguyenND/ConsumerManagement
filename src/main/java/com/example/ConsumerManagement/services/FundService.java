package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.Fund;

import java.util.Optional;

public interface FundService {
    Optional<Fund> findById(Integer integer);

    void deleteById(Integer integer);

    <S extends Fund> S save(S s);

    String findOwnerByFundId(Integer fundId);

    void changeOwner(Integer fundId, String newOwner);
}
