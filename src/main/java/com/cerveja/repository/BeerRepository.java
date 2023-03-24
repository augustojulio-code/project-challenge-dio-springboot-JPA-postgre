package com.cerveja.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cerveja.model.Beer;
@Repository
public interface BeerRepository extends JpaRepository<Beer,UUID>{

}
