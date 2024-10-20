package br.com.exchange.dto;

public record ExchangeDTO(String result, String base_code, String target_code, Double conversion_rate) {
}
