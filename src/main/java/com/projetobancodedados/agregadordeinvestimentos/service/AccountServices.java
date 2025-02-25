package com.projetobancodedados.agregadordeinvestimentos.service;

import com.projetobancodedados.agregadordeinvestimentos.controller.client.BrapiClient;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.AccountStockResponseDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.AssocietesAccountStockDto;
import com.projetobancodedados.agregadordeinvestimentos.entity.AccountStock;
import com.projetobancodedados.agregadordeinvestimentos.entity.AccountStockId;
import com.projetobancodedados.agregadordeinvestimentos.repository.AccountRepository;
import com.projetobancodedados.agregadordeinvestimentos.repository.AccountStockRepository;
import com.projetobancodedados.agregadordeinvestimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServices {
    @Value("#{environment.TOKEN}")
    private String TOKEN;
    private AccountRepository accountRepository;

    private StockRepository stockRepository;

    private AccountStockRepository accountStockRepository;
    @Autowired
    private BrapiClient brapiClient;
    public AccountServices(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }

    public void associetesStock(String accountsId, AssocietesAccountStockDto associetesAccountStockDto) {
        var account = accountRepository.findById(UUID.fromString(accountsId))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        var stock = stockRepository.findById(associetesAccountStockDto.stockId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entiy = new AccountStock(
                id,
                account,
                stock,
                associetesAccountStockDto.quantity()
        );
        accountStockRepository.save(entiy);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(as ->
                        new AccountStockResponseDto(
                                as.getStock().getStockId(),
                                as.getQuantity(),
                                getTotal(as.getQuantity(), as.getStock().getStockId())
                        ))
                .toList();
    }

    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }
}
