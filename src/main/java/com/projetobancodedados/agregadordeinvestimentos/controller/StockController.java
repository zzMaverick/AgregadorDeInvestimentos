package com.projetobancodedados.agregadordeinvestimentos.controller;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateStockDto;
import com.projetobancodedados.agregadordeinvestimentos.service.StockServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockServices stockServices;
    public StockController(StockServices stockServices){
        this.stockServices = stockServices;
    }
    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto){

        stockServices.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
