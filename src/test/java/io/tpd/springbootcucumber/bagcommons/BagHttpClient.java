package io.tpd.springbootcucumber.bagcommons;

import io.tpd.springbootcucumber.Bag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class BagHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String THINGS_ENDPOINT = "/things";

    @LocalServerPort
    private int port;

    @Value("${application.apiUrl}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public int put(final String something) {
        return restTemplate.postForEntity(this.apiUrl, something, Void.class).getStatusCodeValue();
    }

    public Bag getContents() {
        return restTemplate.getForEntity(this.apiUrl, Bag.class).getBody();
    }

    public void clean() {
        restTemplate.delete(this.apiUrl);
    }


}
