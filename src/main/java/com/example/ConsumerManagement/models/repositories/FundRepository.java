package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import org.springframework.data.repository.CrudRepository;

public interface FundRepository extends CrudRepository<Fund, Integer> {
}
