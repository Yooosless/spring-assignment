package com.example.webclient.client;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class responseClient {

    public void invokePayuApi() {
        WebClient webClient = WebClient.create();

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("key", "JEnhDf");
        requestBody.add("command", "get_Transaction_Details");
        requestBody.add("var1", "2022-08-05");
        requestBody.add("var2", "2022-08-06");
        requestBody.add("hash", "eb76c2a2ae354161808d39d45e2c333434302d6185aa55f9605074e32deb9468cab2451a70c581eddcbd183415d877032e5ddc0ac8b5c2c225c17d7cc6a327c5");

        Mono<String> responseMono = webClient.post()
                .uri("https://info.payu.in/merchant/postservice.php?form=2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);

        responseMono.subscribe(responseBody -> {
            System.out.println("Response from PayU: " + responseBody);
        });
    }
}

