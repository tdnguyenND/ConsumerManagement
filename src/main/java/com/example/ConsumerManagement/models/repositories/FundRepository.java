package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FundRepository extends CrudRepository<Fund, Integer> {
    @Query("SELECT owner from Fund where fundId = :fundId")
    String findOwnerByFundId(int fundId);

    @Modifying
    @Query(value = "update Fund set owner = :newOwner where fundId = :fundId")
    void changeOwner(int fundId, String newOwner);

    @Modifying
    @Query("update Fund set balance = :balance where fundId = :fundId")
    void updateBalance(int fundId, double balance);
}
