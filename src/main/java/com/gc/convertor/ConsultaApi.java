package com.gc.convertor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class ConsultaApi {

    Divisas conseguirDivisa(){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/09c47ef65ace5f2a98b279d7/latest/USD");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), Divisas.class);
    }

}
