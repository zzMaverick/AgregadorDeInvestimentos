package com.projetobancodedados.agregadordeinvestimentos.repository;

import com.projetobancodedados.agregadordeinvestimentos.entity.AccountStock;
import com.projetobancodedados.agregadordeinvestimentos.entity.AccountStockId;
import com.projetobancodedados.agregadordeinvestimentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
