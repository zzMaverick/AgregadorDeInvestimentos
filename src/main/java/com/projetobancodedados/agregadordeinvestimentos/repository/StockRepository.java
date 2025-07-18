package com.projetobancodedados.agregadordeinvestimentos.repository;

import com.projetobancodedados.agregadordeinvestimentos.entity.Stock;
import com.projetobancodedados.agregadordeinvestimentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
