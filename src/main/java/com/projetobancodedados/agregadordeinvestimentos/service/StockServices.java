package com.projetobancodedados.agregadordeinvestimentos.service;

import com.projetobancodedados.agregadordeinvestimentos.controller.client.BrapiClient;
import com.projetobancodedados.agregadordeinvestimentos.controller.dto.CreateStockDto;
import com.projetobancodedados.agregadordeinvestimentos.entity.Stock;
import com.projetobancodedados.agregadordeinvestimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockServices {
    private StockRepository stockRepository;
    @Value("#{environment.TOKEN}")
    private String TOKEN;
    @Autowired
    private BrapiClient brapiClient;

    public StockServices(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.descripition(),
                createStockDto.value()
        );
        var response = brapiClient.getQuote(TOKEN, stock.getStockId());
        stock.setValue(response.results().getFirst().regularMarketPrice());
        stockRepository.save(stock);
    }

}
