package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import org.springframework.data.repository.CrudRepository;

public interface UserFundRepository extends CrudRepository<UserFund, UserFundKey> {
}
