package br.com.exchange.api;

import br.com.exchange.dto.ExchangeDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeAPI {

    public ExchangeDTO request(String currentCoin, String toCoin) {

        try {
            String api = "https://v6.exchangerate-api.com/v6/2278435562a4630dffecab9d/pair/"+currentCoin+"/"+toCoin;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(api))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && !response.body().isEmpty()) {
                Gson gson = new Gson();
                return gson.fromJson(response.body(), ExchangeDTO.class);
            }
        } catch (InterruptedException | IOException err) {
            System.out.println("Erro: " + err);
        }

        return null;
    }
}
