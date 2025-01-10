package com.aquirozc.literalura.net;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import com.aquirozc.literalura.transitional.BookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GutendexAPIClient {

    private static final String URL_TEMPLATE = "https://gutendex.com/books?search=%s";

    private HttpClient client = HttpClient.newBuilder().followRedirects(Redirect.ALWAYS).build();
    private ObjectMapper mapper = new ObjectMapper();

    public List<BookDTO> queryBook(String tittle) throws InterruptedException, IOException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(String.format(URL_TEMPLATE, tittle.replace(" ", " %20"))))
                                    .GET()
                                    .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        if (response.statusCode() != 200) throw new IOException();

        return mapper.readValue(response.body(), GutendexAPIResponse.class).getResults();

    }
    
}

