package com.projetobancodedados.agregadordeinvestimentos.controller.client.dto;

import java.util.List;

public record BrapiResponseDto(List<StockDto> results) {
}
