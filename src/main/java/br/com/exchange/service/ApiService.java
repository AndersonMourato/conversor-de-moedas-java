package br.com.exchange.service;

import br.com.exchange.api.ExchangeAPI;

public class ApiService {
    private final ExchangeAPI api;

    public ApiService() {
        this.api = new ExchangeAPI();
    }

    public Double getCoinTaxa(String currentCoin, String toCoin) {
        return this.api.request(currentCoin, toCoin).conversion_rate();
    }

}
