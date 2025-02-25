package com.projetobancodedados.agregadordeinvestimentos.controller;

import com.projetobancodedados.agregadordeinvestimentos.controller.dto.AccountResponseDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.AccountStockResponseDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.AssocietesAccountStockDto;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateAccountDto;
import com.projetobancodedados.agregadordeinvestimentos.service.AccountServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }
    @PostMapping("{accountsId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountsId") String accountsId,
                                               @RequestBody AssocietesAccountStockDto associetesAccountStockDto){
        accountServices.associetesStock(accountsId,associetesAccountStockDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("{accountsId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> stockList(@PathVariable("accountsId") String accountsId){

        List<AccountStockResponseDto> stockList = accountServices.listStocks(accountsId);
        return ResponseEntity.ok(stockList);
    }
}
