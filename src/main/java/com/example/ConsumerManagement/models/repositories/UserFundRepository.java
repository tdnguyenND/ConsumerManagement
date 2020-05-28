package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserFundRepository extends CrudRepository<UserFund, UserFundKey> {
    @Query("select username from UserFund where fundId = :fundId")
    Iterable<String> findAllMemberByFundId(int fundId);

    @Query("select fundId from UserFund where username = :username")
    Iterable<Integer> findAllFundIdByUsername(String username);

    void deleteAllByFundId(int fundId);
}
