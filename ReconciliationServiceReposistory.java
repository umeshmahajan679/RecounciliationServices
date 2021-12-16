package com.example.demo.Reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.ReconciliationServiceModel;

public interface ReconciliationServiceReposistory extends JpaRepository<ReconciliationServiceModel, Long>{

}
