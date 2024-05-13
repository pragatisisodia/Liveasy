package com.example.liveasy.repository;

import com.example.liveasy.entity.Load;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LoadRepository extends CrudRepository<Load,Integer> {
    List<Load> findByShipperId(UUID shipperId);
}
